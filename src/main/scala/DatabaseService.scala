import Tables.{Task,User,TaskTable,UserTable}
import slick.jdbc.PostgresProfile.api.*

import scala.concurrent.{ExecutionContext, Future}

class DatabaseService(db: Database)(implicit ec: ExecutionContext) {
  val tasks = TableQuery[TaskTable]
  val users = TableQuery[UserTable]
  def createTask(task: Task): Future[Task] = {
    val action = (tasks returning tasks.map(_.id) into ((task, id) => task.copy(id))) += task
    db.run(action)
  }
  def getTask(id: Long): Future[Option[Task]] = db.run(tasks.filter(_.id === id).result.headOption)
  def updateTask(task: Task): Future[Int] = db.run(tasks.filter(_.id === task.id).update(task))
  def deleteTask(id: Long): Future[Int] = db.run(tasks.filter(_.id === id).delete)
  def createUser(user: User): Future[User] = {
    val action = (users returning users.map(_.id) into ((user, id) => user.copy(id))) += user
    db.run(action)
  }
  def getUser(id: Long): Future[Option[User]] = db.run(users.filter(_.id === id).result.headOption)
  def updateUser(user: User): Future[Int] = db.run(users.filter(_.id === user.id).update(user))
  def deleteUser(id: Long): Future[Int] = db.run(users.filter(_.id === id).delete)
  def getUserTasks(userId: Long): Future[Seq[Task]] = db.run(tasks.filter(_.userId === userId).result)
}
