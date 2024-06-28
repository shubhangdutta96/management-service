import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "task-buddy",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.5.0",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.5.1",
      "org.postgresql" % "postgresql" % "42.2.18",
      "com.typesafe.akka" %% "akka-http" % "10.5.3",
      "com.typesafe" % "config" % "1.4.1",
      "com.typesafe.akka" % "akka-http-spray-json_3" % "10.6.0-M1",
      "com.typesafe.akka" %% "akka-testkit" % "2.9.0-M2" % Test,
      "com.typesafe.akka" %% "akka-http-testkit" % "10.5.3" % Test,
      "io.spray" %% "spray-json" % "1.3.6",
      "ch.qos.logback" % "logback-classic" % "1.5.6",
      "com.typesafe.akka" %% "akka-actor-typed" % "2.9.0-M2",
      "com.typesafe.akka" %% "akka-stream" % "2.9.0-M2",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
      "org.flywaydb" % "flyway-core" % "7.7.0",
      "com.github.pureconfig" %% "pureconfig-enumeratum" % "0.17.6",
      "org.scalatest" %% "scalatest" % "3.2.18" % Test,
      "com.typesafe.akka" %% "akka-slf4j" % "2.9.0-M2",
      "org.scalatestplus" %% "mockito-3-4" % "3.2.9.0" % Test,
    )
  )
