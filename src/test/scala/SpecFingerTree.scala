package fingertrees

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.collection.View.Single
import fingertree.implFingerTree.FingerTree

class SpecFingerTree extends AnyWordSpec with Matchers {

  "A Single" when {

    val fingerTree: FingerTree[Int] = FingerTree()

    "calling toString" should {
      "be presented right" in {
        fingerTree.toString should be("Empty()")
      }
    }
  }

}
