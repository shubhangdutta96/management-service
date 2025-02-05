import Tables.{Task,User}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import slick.jdbc.PostgresProfile.api.*

import scala.concurrent.duration.*

class TaskRoutesSpec extends AnyWordSpec with Matchers with ScalatestRouteTest with ScalaFutures with JsonSupport {
  implicit val ec: Any = system.dispatcher

  // Set a longer timeout duration
  implicit val defaultPatience: PatienceConfig = PatienceConfig(timeout = 5.seconds, interval = 100.millis)

  // Mock database service
  val mockDb = Database.forConfig("slick.dbs.default.db")
  val mockDatabaseService = new DatabaseService(mockDb)

  val taskRoutes = new TaskRoutes(mockDatabaseService)

  "TaskRoutes" should {
    // Task tests
    "return a task for GET requests to /tasks/{id}" in {
      val newTask = Task(0, "Test Task", "Test Description", 1)
      val savedTask = mockDatabaseService.createTask(newTask).futureValue

      Get(s"/tasks/${savedTask.id}") ~> taskRoutes.route ~> check {
        status shouldBe StatusCodes.OK
        val task = responseAs[Task]
        task.title shouldEqual "Test Task"
      }
    }

    "create a task for POST requests to /tasks" in {
      val newTask = Task(0, "New Task", "New Description", 1)
      Post("/tasks", newTask) ~> taskRoutes.route ~> check {
        status shouldBe StatusCodes.OK
        val task = responseAs[Task]
        task.title shouldEqual "New Task"
      }
    }

    "update a task for PUT requests to /tasks/{id}" in {
      val newTask = Task(0, "Test Task", "Test Description", 1)
      val savedTask = mockDatabaseService.createTask(newTask).futureValue

      val updatedTask = savedTask.copy(title = "Updated Task")
      Put(s"/tasks/${savedTask.id}", updatedTask) ~> taskRoutes.route ~> check {
        status shouldBe StatusCodes.OK
        responseAs[String] shouldEqual "Task updated"
      }
    }

    "delete a task for DELETE requests to /tasks/{id}" in {
      val newTask = Task(0, "Test Task", "Test Description", 1)
      val savedTask = mockDatabaseService.createTask(newTask).futureValue

      Delete(s"/tasks/${savedTask.id}") ~> taskRoutes.route ~> check {
        status shouldBe StatusCodes.OK
        responseAs[String] shouldEqual "Task deleted"
      }
    }

// User tests
    "return a user for GET requests to /users/{id}" in {
      val newUser = User(0, "Test User", "testuser@example.com")
      val savedUser = mockDatabaseService.createUser(newUser).futureValue

      Get(s"/users/${savedUser.id}") ~> taskRoutes.route ~> check {
        status shouldBe StatusCodes.OK
        val user = responseAs[User]
        user.name shouldEqual "Test User"
      }
    }

    "create a user for POST requests to /users" in {
      val newUser = User(0, "New User", "newuser@example.com")
      Post("/users", newUser) ~> taskRoutes.route ~> check {
        status shouldBe StatusCodes.OK
        val user = responseAs[User]
        user.name shouldEqual "New User"
      }
    }

    "update a user for PUT requests to /users/{id}" in {
      val newUser = User(0, "Test User", "testuser@example.com")
      val savedUser = mockDatabaseService.createUser(newUser).futureValue

      val updatedUser = savedUser.copy(name = "Updated User")
      Put(s"/users/${savedUser.id}", updatedUser) ~> taskRoutes.route ~> check {
        status shouldBe StatusCodes.OK
        responseAs[String] shouldEqual "User updated"
      }
    }

    "delete a user for DELETE requests to /users/{id}" in {
      val newUser = User(0, "Test User", "testuser@example.com")
      val savedUser = mockDatabaseService.createUser(newUser).futureValue

      Delete(s"/users/${savedUser.id}") ~> taskRoutes.route ~> check {
        status shouldBe StatusCodes.OK
        responseAs[String] shouldEqual "User deleted"
      }
    }
  }
}
