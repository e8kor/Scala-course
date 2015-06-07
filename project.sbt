scalaVersion := "2.11.6"

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-feature",
  "-language:implicitConversions", "-language:higherKinds", "-language:postfixOps"
)

lazy val root = project in file(".") aggregate core

lazy val core = project in file("core")