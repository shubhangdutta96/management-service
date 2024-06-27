import Tables.{Task,User}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val taskFormat: RootJsonFormat[Task] = jsonFormat4(Task)
  implicit val userFormat: RootJsonFormat[User] = jsonFormat3(User)
}
