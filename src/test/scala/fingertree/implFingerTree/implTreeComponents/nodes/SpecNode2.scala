package fingertree.implFingerTree.implTreeComponents.nodes

import deep.IDeep
import deep.implDeep.Deep
import digit.IDigit
import digit.implDigit.Digit1
import empty.IEmpty
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import node.INode
import node.implNode.Node3
import node.nodeImpl.Node2
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.implSingle.Single

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
        val size: Int = node.size

        size should be(2)
      }

      "be 0 when Emptys are stored" in {
        when(mockedEmpty.size) thenReturn 0
        val node: INode[ITreeComponent[Nothing]] =
          Node2(mockedEmpty, mockedEmpty)

        val size: Int = node.size

        size should be(0)
      }

      "be 1 when Empty and Single are stored" in {
        when(mockedEmpty.size) thenReturn 0
        when(mockedSingle.size) thenReturn 1
        val node: INode[ITreeComponent[Int]] = Node2(mockedEmpty, mockedSingle)

        val size: Int = node.size

        size should be(1)
      }

      "be 4 when 2 Deep( Digit1 Empty Digit1 ) are stored" in {
        when(mockedDeep.size) thenReturn 2
        val node: INode[ITreeComponent[Int]] = Node2(mockedDeep, mockedDeep)

        val size: Int = node.size

        size should be(4)
      }

      "be 2 when 2 Digit1 are stored" in {
        when(mockedDigit.size) thenReturn 1
        val node: INode[IDigit[Int]] = Node2(mockedDigit, mockedDigit)

        val size: Int = node.size

        size should be(2)
      }

      "be 6 when 2 Node3 are stored" in {
        when(mockedNode.size) thenReturn 3
        val node: INode[INode[Int]] = Node2(mockedNode, mockedNode)

        val size: Int = node.size

        size should be(6)
      }
    }

    "checking if its empty" should {
      "be false" in {
        val isEmpty: Boolean = node.isEmpty

        isEmpty should be(false)
      }
    }

    "calling toList" should {
      "return entries as List" in {
        val list: List[Int] = node.toList

        val expectedList: List[Int] = List(10, 9)
        list should be(expectedList)
      }
    }

    "calling toString" should {
      "be presented right when only created" in {
        val stringRepresentation: String = node.toString

        val expectedString: String = "Node( 10, 9 )"
        stringRepresentation should be(expectedString)
      }
    }
  }
}
