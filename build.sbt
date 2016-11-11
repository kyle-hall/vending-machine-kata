
import sbt.Keys._

val root = (project in file("."))
  .settings(
    name := """vending-machine-kata""",
    version := "0.1.0",
    scalaVersion := "2.11.8",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.5"
  )
