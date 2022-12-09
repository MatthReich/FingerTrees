package fingertrees.nodes

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import node.nodeImpl.Node2

class SpecNode2 extends AnyWordSpec with Matchers {

  "A Node with 2 elements" when {

    val node: Node2[Int] = Node2(10, 9)

    "checking size" should {
      "return 2" in {
        node.size should be(2)
      }
    }

    "checking if its empty" should {
      "be false" in {
        node.isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return right head element" in {
        node.head should be(Some(10))
      }
    }

    "accessing last" should {
      "return right last element" in {
        node.last should be(Some(9))
      }
    }

    "calling toString" should {
      "be presented right when only created" in {
        node.toString should be("Node( 10, 9 )")
      }
    }
  }
}
