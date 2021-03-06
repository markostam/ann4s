package ann4s.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, Suite}

trait LocalSparkContext extends BeforeAndAfterAll { self: Suite =>

  @transient private var _sc: SparkContext = _

  def sc: SparkContext = _sc

  var conf = new SparkConf(false).setMaster("local").setAppName("test")

  override def beforeAll() {
    _sc = new SparkContext(conf)
    super.beforeAll()
  }

  override def afterAll() {
    if (_sc != null) _sc.stop()
    System.clearProperty("spark.driver.port")
    _sc = null
    super.afterAll()
  }

}
