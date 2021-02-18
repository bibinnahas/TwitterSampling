
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
//  "org.apache.spark" % "spark-streaming_2.12" % "3.0.0-preview" % "provided",
//  "org.apache.spark" %% "spark-streaming-twitter" % "1.6.0",
  "org.apache.bahir" %% "spark-streaming-twitter" % "2.0.0",
  "org.twitter4j" % "twitter4j-stream" % "3.0.3",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "com.holdenkarau" %% "spark-testing-base" % "2.4.5_0.14.0"
)

//resolvers += "twitter_2" at "https://mvnrepository.com/artifact/org.apache.spark/spark-streaming-twitter"
//libraryDependencies += "org.apache.spark" % "spark-streaming-twitter_2.11" % "1.6.3"

//dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.7"
//
//dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7"
//
//dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.7"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}