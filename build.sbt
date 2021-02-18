
name := "TwitterSampling"

version := "0.1"

scalaVersion := "2.11.12"

val sparkVersion = "2.0.0"

lazy val root = (project in file(".")).
  settings(
    name := "TwitterSampling",
    version := "1.0",
    scalaVersion := "2.11.12",
    mainClass in Compile := Some("com.me.twitter.entry.PrintTweets")
  )

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-catalyst" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "org.apache.bahir" %% "spark-streaming-twitter" % "2.0.0",
  "org.twitter4j" % "twitter4j-stream" % "3.0.3"

  //  "org.apache.spark" % "spark-streaming_2.12" % "3.0.0-preview" % "provided",
  //  "org.apache.spark" %% "spark-streaming-twitter" % "1.6.0",
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}