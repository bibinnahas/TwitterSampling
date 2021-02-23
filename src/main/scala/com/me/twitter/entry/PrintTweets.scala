package com.me.twitter.entry

import com.me.twitter.entry.Utilities.{setupLogging, setupTwitter}
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/** Simple application to listen to a stream of Tweets and print them out */
object PrintTweets {

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Configure Twitter credentials using twitter.txt
    setupTwitter()
    val ssc = new StreamingContext("local[*]", "PrintTweets", Seconds(1))

    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    val tweets = TwitterUtils.createStream(ssc, None)
    val statuses = tweets.map(status => status.getText())

    //    print tweets
    //    statuses.print()

    var totalTweets: Long = 0
    statuses.foreachRDD((rdd, time) => {
      if (rdd.count > 0) {
        val repartitionRdd = rdd.coalesce(1).cache()
        repartitionRdd.saveAsTextFile(s"Tweets_${time.milliseconds.toString}")
        totalTweets += repartitionRdd.count()
        println(s"Toatal tweets so far: $totalTweets")
        if (totalTweets > 1000) System.exit(0)
      }
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
