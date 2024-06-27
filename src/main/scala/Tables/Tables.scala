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

upled: ((Long, String, String)) => User = (User.apply _).tupled
}

class TaskTable(tag: Tag) extends Table[Task](tag, "tasks") {
  def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def title: Rep[String] = column[String]("title")
  def description: Rep[String] = column[String]("description")
  def userId: Rep[Long] = column[Long]("user_id")

  def * : ProvenShape[Task] = (id, title, description, userId) <> (Task.tupled, Task.unapply)
}

class UserTable(tag: Tag) extends Table[User](tag, "users") {
  def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name: Rep[String] = column[String]("name")
  def email: Rep[String] = column[String]("email")

  def * : ProvenShape[User] = (id, name, email) <> (User.tupled, User.unapply)
}
