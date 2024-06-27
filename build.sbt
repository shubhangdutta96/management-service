ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

val akkaVersion = "2.8.5"

lazy val root = (project in file("."))
  .settings(
    name := "task-buddy",
    
  )
