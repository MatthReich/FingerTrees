package fingertrees.nodes

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import node.nodeImpl.Node2

class SpecNode2 extends AnyWordSpec with Matchers {

  "A Node with 2 elements" when {

    val node: Node2[Int] = Node2(10, 9)

    "calling toString" should {
      "be presented right when only created" in {
        node.toString should be("Node( 10, 9 )")
      }
    }
  }
}