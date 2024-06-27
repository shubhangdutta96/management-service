import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContextExecutor

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("taskManagementSystem")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val config = ConfigFactory.load()
  val db = Database.forConfig("slick.dbs.default.db")

  val databaseService = new DatabaseService(db)
  val taskRoutes = new TaskRoutes(databaseService)

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(taskRoutes.route)

  println(s"Server online at http://localhost:8080/")
}
