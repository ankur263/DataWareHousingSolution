package io.smx.spark.news

import org.apache.spark.sql.SchemaRDD
import org.apache.spark.streaming.Minutes
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkContext

object RecommendLinksDrive {
 def main(args: Array[String]) {
  val sc = new SparkContext()

  val sqlContext = new org.apache.spark.sql.SQLContext(sc)
  val tweetsRDD = createDataset(sqlContext, "./urls2", 1448195100, 1448195460)

  tweetsRDD.registerAsTable("tweets")
  sqlContext.cacheTable("tweets")
  val query = args(0)
  var flag = args(1).toInt
  
  // Show the SchemaRDD
  tweetsRDD.printSchema()

  import sqlContext._
    val topLinks = sql(query)
    if(flag == 2)
      prettyResults(topLinks.collect)
    else if (flag == 1)
      prettyResults1(topLinks.collect())
    else
        println(f"\nEnter correct flag")
 
}  
  // ------------------------------
  
  
  def folderExists(basePath: String, id: Int) = new java.io.File(basePath + "/" + id).exists()

  def createDataset(sqlContext: SQLContext, basePath: String, from: Int, to: Int): SchemaRDD = {
    val minute1 = 6
     // 1min in seconds
    val rddId = Range(from, to + 1, minute1)

    val schemaRDDs = rddId.filter(folderExists(basePath, _)).map { id =>
      sqlContext.parquetFile(basePath + "/" + id)
    }
    schemaRDDs.reduce((s1, s2) => s1.unionAll(s2))
  }
  
  def prettyResults(rows: Array[org.apache.spark.sql.Row]) {
    rows.foreach(r =>{
      val count = r.getLong(1)
      val url = r.getString(0)
      println(f"  $url    $count")
    })
      
    println("\n\n")
  }
  def prettyResults1(rows: Array[org.apache.spark.sql.Row]) {
    rows.foreach(r =>{
      val url = r.getString(0)
      println(f"  $url")
    })
      
    println("\n\n")
  }

}