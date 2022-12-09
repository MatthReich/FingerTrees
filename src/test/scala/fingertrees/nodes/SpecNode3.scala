package fingertrees.nodes

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import node.implNode.Node3

class SpecNode3 extends AnyWordSpec with Matchers {

  "A Node with 3 elements" when {

    val node: Node3[Int] = Node3(10, 9, 8)

    "checking size" should {
      "be 3" in {
        node.size should be(3)
      }
    }

    "accessing head" should {
      "return right head element" in {
        node.head should be(Some(10))
      }
    }

    "accessing last" should {
      "return right last element" in {
        node.last should be(Some(8))
      }
    }

    "calling toString" should {
      "be presented right when only created" in {
        node.toString should be("Node( 10, 9, 8 )")
      }
    }
  }
}
