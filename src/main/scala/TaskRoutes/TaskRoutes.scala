import Tables.{Task, User}
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import com.typesafe.scalalogging.LazyLogging
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
import spray.json.DefaultJsonProtocol.*

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import akka.http.scaladsl.model.StatusCodes

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class TaskRoutes(databaseService: DatabaseService)(implicit ec: ExecutionContext) extends JsonSupport {

  val route: Route =
    pathPrefix("tasks") {
      pathEndOrSingleSlash {
        post {
          entity(as[Task]) { task =>
            onSuccess(databaseService.createTask(task)) { createdTask =>
              complete(createdTask)
            }
          }
        }
      } ~
        path(LongNumber) { id =>
          get {
            rejectEmptyResponse {
              complete(databaseService.getTask(id))
            }
          } ~
            put {
              entity(as[Task]) { task =>
                onSuccess(databaseService.updateTask(task)) { _ =>
                  complete("Task updated")
                }
              }
            } ~
            delete {
              onSuccess(databaseService.deleteTask(id)) { _ =>
                complete("Task deleted")
              }
            }
        }
    } ~
      pathPrefix("users") {
        pathEndOrSingleSlash {
          post {
            entity(as[User]) { user =>
              onSuccess(databaseService.createUser(user)) { createdUser =>
                complete(createdUser)
              }
            }
          }
        } ~
          path(LongNumber) { id =>
            get {
              rejectEmptyResponse {
                complete(databaseService.getUser(id))
              }
            }
      }
    }
}
