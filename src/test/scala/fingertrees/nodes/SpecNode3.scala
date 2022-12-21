package fingertrees.nodes

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import node.implNode.Node3
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
import node.nodeImpl.Node2
import digit.IDigit

class SpecNode3 extends AnyWordSpec with Matchers {

  "A Node with 3 elements" when {

    val node: INode[Int] = Node3(10, 9, 8)
    val mockedEmpty = mock(classOf[Empty])
    val mockedDigit = mock(classOf[Digit1[Int]])
    val mockedSingle = mock(classOf[Single[Int]])
    val mockedDeep = mock(classOf[Deep[Int]])
    val mockedNode = mock(classOf[Node2[Int]])
  
    "checking size" should {
      "be 3" in {
        node.size should be(3)
      }
    }

    "checking size" should {
      "be 3 when only values are stored" in {
        node.size should be(3)
      }

      "be 0 when Emptys are stored" in {
        when(mockedEmpty.size) thenReturn 0
        val node: INode[ITreeComponent[Nothing]] = Node3(mockedEmpty, mockedEmpty, mockedEmpty)

        node.size should be(0)
      }

      "be 2 when Empty and 2 Single are stored" in {
        when(mockedEmpty.size) thenReturn 0
        when(mockedSingle.size) thenReturn 1
        val node: INode[ITreeComponent[Int]] = Node3(mockedSingle, mockedEmpty, mockedSingle)

        node.size should be(2)
      }

      "be 6 when 3 Deep( Digit1 Empty Digit1 ) are stored" in {
        when(mockedDeep.size) thenReturn 2
        val node: INode[ITreeComponent[Int]] = Node3(mockedDeep, mockedDeep, mockedDeep)

        node.size should be(6)
      }

      "be 3 when 3 Digit1 are stored" in {
        when(mockedDigit.size) thenReturn 1
        val node: INode[IDigit[Int]] = Node3(mockedDigit, mockedDigit, mockedDigit)

        node.size should be(3)
      }

      "be 6 when 3 Node2 is stored" in {
        when(mockedNode.size) thenReturn 2
        val node: INode[INode[Int]] = Node3(mockedNode, mockedNode, mockedNode)

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
        node.toString should be("Node( 10, 9, 8 )")
      }
    }
  }
}
