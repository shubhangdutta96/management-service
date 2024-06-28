import Tables.{Task, User}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import scala.concurrent.ExecutionContext


class TaskRoutes(databaseService: DatabaseService)(implicit ec: ExecutionContext) extends JsonSupport {

  val route: Route =
  pathSingleSlash {
    get {
      complete("Welcome to the Task Management Service API")
    }
  } ~
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
            } ~
              put {
                entity(as[User]) { user =>
                  onSuccess(databaseService.updateUser(user)) { _ =>
                    complete("User updated")
                  }
                }
              } ~
              delete {
                onSuccess(databaseService.deleteUser(id)) { _ =>
                  complete("User deleted")
                }
              }
        }
    }
}
