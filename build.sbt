lazy val mainSettings = Seq(
  name := "jobserver-cassandra",
  organization := "spark.jobserver",
  crossScalaVersions := Seq("2.10.4","2.11.6"),
  scalacOptions ++= Seq("-Xlint", "-deprecation", "-Xfatal-warnings", "-feature")
)

libraryDependencies ++= Seq(
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.4.0" % "provided",
  "spark.jobserver"    %% "job-server"                % "0.6.1" % "provided"
)

lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

lazy val styleSettings = Seq(
  scalastyleFailOnError := true,
  compileScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value,
  // Is running this on compile too much?
  // (compile in Compile) <<= (compile in Compile) dependsOn compileScalastyle
  (Keys.`package` in Compile) <<= (Keys.`package` in Compile) dependsOn compileScalastyle
)

lazy val publishSettings = Seq(
  licenses += ("Apache-2.0", url("http://choosealicense.com/licenses/apache/")),
  bintrayOrganization := Some("spark-jobserver")
)

mainSettings ++ styleSettings ++ publishSettings