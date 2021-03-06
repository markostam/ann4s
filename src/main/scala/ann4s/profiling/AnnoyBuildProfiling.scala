package ann4s.profiling

import ann4s.AnnoyIndex

object AnnoyBuildProfiling  {

  import AnnoyDataset._

  def main(args: Array[String]) {

    val f = dataset.head.length
    elapsed("build", 3000, logging = true) {
      val i = new AnnoyIndex(f)
      dataset.zipWithIndex.foreach { case (v, j) =>
        i.addItem(j, v)
      }
      i.build(10)
      i.save("annoy-index-scala")
      i.unload()

    }
  }
}
