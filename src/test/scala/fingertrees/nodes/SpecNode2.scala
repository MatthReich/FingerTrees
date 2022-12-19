package fingertrees.nodes

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import node.nodeImpl.Node2
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import empty.implEmpty.Empty
import digit.implDigit.Digit1
import single.implSingle.Single
import deep.implDeep.Deep
import node.INode
import empty.IEmpty
import fingertree.implFingerTree.ITreeComponent
import deep.IDeep
import node.implNode.Node3

class SpecNode2 extends AnyWordSpec with Matchers {

  "A Node with 2 elements" when {

    val node: INode[Int] = Node2(10, 9)
    val mockedEmpty = mock(classOf[Empty])
    val mockedDigit = mock(classOf[Digit1[Int]])
    val mockedSingle = mock(classOf[Single[Int]])
    val mockedDeep = mock(classOf[Deep[Int]])
    val mockedNode = mock(classOf[Node3[Int]])

     "checking size" should {
      "be 2 when only values are stored" in {
        node.size should be(2)
      }

      "be 0 when Emptys are stored" in {
        when(mockedEmpty.size) thenReturn 0
        val node: INode[ITreeComponent[Nothing]] = Node2(mockedEmpty, mockedEmpty)

        node.size should be(0)
      }

      "be 1 when Empty and Single are stored" in {
        when(mockedEmpty.size) thenReturn 0
        when(mockedSingle.size) thenReturn 1
        val node: INode[ITreeComponent[Int]] = Node2(mockedEmpty, mockedSingle)

        node.size should be(1)
      }

      "be 4 when 2 Deep( Digit1 Empty Digit1 ) are stored" in {
        when(mockedDeep.size) thenReturn 2
        val node: INode[ITreeComponent[Int]] = Node2(mockedDeep, mockedDeep)

        node.size should be(4)
      }

      "be 6 when 2 Node3 is stored" in {
        when(mockedNode.size) thenReturn 3
        val node: INode[INode[Int]] = Node2(mockedNode, mockedNode)

        node.size should be(6)
      }
    }

    "checking if its empty" should {
      "be false" in {
        node.isEmpty should be(false)
      }
    }




    "calling toString" should {
      "be presented right when only created" in {
        node.toString should be("Node( 10, 9 )")
      }
    }
  }
}
