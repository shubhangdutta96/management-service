package Tables

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

case class Task(id: Long, title: String, description: String, userId: Long)
object Task {
  val tupled: ((Long, String, String, Long)) => Task = (Task.apply _).tupled
}
case class User(id: Long, name: String, email: String)
object User {
  val tupled: ((Long, String, String)) => User = (User.apply _).tupled
}
