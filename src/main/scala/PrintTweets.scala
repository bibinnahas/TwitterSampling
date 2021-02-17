import Utilities._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.twitter.TwitterUtils

/** Simple application to listen to a stream of Tweets and print them out */
object PrintTweets {
 
  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Configure Twitter credentials using twitter.txt
    setupTwitter()
    val ssc = new StreamingContext("local[*]", "PrintTweets", Seconds(5))

    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    val tweets = TwitterUtils.createStream(ssc, None)
    val statuses = tweets.map(status => status.getText())
    statuses.print()
    ssc.start()
    ssc.awaitTermination()
  }  
}