package fingertrees

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import fingertree.implFingerTree.FingerTree
import fingertree.implFingerTree.ITreeComponent

class SpecFingerTree extends AnyWordSpec with Matchers {

  "A FingerTree" when {

    val fingerTree: FingerTree[Int] = FingerTree()

    "calling toString" should {
      "be presented right when only created" in {
        fingerTree.toString should be("Empty()")
      }
    }
  }

}
