package fingertrees.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import empty.implEmpty.Empty
import node.implNode.Node3
import deep.implDeep.Deep
import digit.IDigit
import fingertree.implFingerTree.ITreeComponent
import node.INode
import single.implSingle.Single
import digit.implDigit.Digit1
import digit.implDigit.Digit2
import digit.implDigit.Digit3
import deep.IDeep

class SpecDigits4 extends AnyWordSpec with Matchers {

  "A Digit with 4 element" when {

    val digit: Digit4[Int] = Digit4(10, 9, 8, 7)
    val mockedEmpty = mock(classOf[Empty])
    val mockedDigit = mock(classOf[Digit1[Int]])
    val mockedNode = mock(classOf[Node3[Int]])
    val mockedDeep = mock(classOf[Deep[Int]])
    val mockedSingle = mock(classOf[Single[Int]])

    "appending a new element" should {
      "triggers a sys error" in {
        try {
          val res = digit.:+(5)
          fail()
        } catch {
          case ex: UnsupportedOperationException =>
            ex.getMessage() should be(
              "Digit4 is already max sized. Can´t be appended!"
            )
        }
      }
    }

    "prepending a new element" should {
      "triggers a sys error" in {
        try {
          val res = digit.+:(5)
          fail()
        } catch {
          case ex: UnsupportedOperationException =>
            ex.getMessage() should be(
              "Digit4 is already max sized. Can´t be prepended!"
            )
        }
      }
    }

    "checking size" should {
      "be 4 when 4 Values are stored" in {
        digit.size should be(4)
      }

      "be 0 when 4 Empty are stored" in {
        when(mockedEmpty.size) thenReturn 0
        val digit: IDigit[ITreeComponent[Nothing]] =
          Digit4(mockedEmpty, mockedEmpty, mockedEmpty, mockedEmpty)

        digit.size should be(0)
      }

      "be 4 when 4 Digit1 are stored" in {
        when(mockedDigit.size) thenReturn 1
        val digit: IDigit[IDigit[Int]] =
          Digit4(mockedDigit, mockedDigit, mockedDigit, mockedDigit)

        digit.size should be(4)
      }

      "be 6 when 2 Deep( Digit1 Empty Digit1 ) and 2 Single are stored" in {
        when(mockedDeep.size) thenReturn 2
        when(mockedSingle.size) thenReturn 1
        val digit: IDigit[ITreeComponent[Int]] =
          Digit4(mockedSingle, mockedDeep, mockedSingle, mockedDeep)

        digit.size should be(6)
      }

      "be 12 when 4 Node3 are stored" in {
        when(mockedNode.size) thenReturn 3
        val digit: IDigit[INode[Int]] =
          Digit4(mockedNode, mockedNode, mockedNode, mockedNode)

        digit.size should be(12)
      }
    }

    "checking if its empty" should {
      "be false" in {
        digit.isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return own head when values are stored" in {
        digit.head should be(Some(10))
      }

      "return None when 4 Empty are stored" in {
        when(mockedEmpty.head) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] =
          Digit4(mockedEmpty, mockedEmpty, mockedEmpty, mockedEmpty)

        digit.head should be(None)
      }

      "return head of first Digit when 4 Digit1 are stored" in {
        when(mockedDigit.head) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] =
          Digit4(mockedDigit, Digit1(9), Digit1(9), Digit1(9))

        digit.head should be(Some(10))
      }

      "return head of Deep when Deep( Digit1 Empty Digit1 ) and 3 Single are stored" in {
        when(mockedDeep.head) thenReturn Some(10)
        when(mockedSingle.head) thenReturn Some(9)
        val digit: IDigit[ITreeComponent[Int]] =
          Digit4(mockedDeep, mockedSingle, mockedSingle, mockedSingle)

        digit.head should be(Some(10))
      }

      "return Node3 when 4 Node3 are stored" in {
        val node: INode[Int] = Node3(7, 6, 5)
        val digit: IDigit[INode[Int]] =
          Digit4(mockedNode, node, node, node)

        // @TODO was soll hier passieren? => Maybe None zurück geben

        digit.head should be(Some(mockedNode))
      }
    }

    "accessing last" should {
      "return own entry when only values are stored" in {
        digit.last should be(Some(7))
      }

      "return None when 4 Empty are stored" in {
        when(mockedEmpty.last) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] =
          Digit4(mockedEmpty, mockedEmpty, mockedEmpty, mockedEmpty)

        digit.last should be(None)
      }

      "return last of Digit when 4 Digit1 are stored" in {
        when(mockedDigit.last) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] =
          Digit4(Digit1(9), Digit1(8), Digit1(7), mockedDigit)

        digit.last should be(Some(10))
      }

      "return last of Deep when 4 Deep( Digit1 Empty Digit1 ) are stored" in {
        val deep: IDeep[Int] = Deep(Digit1(11), Empty(), Digit1(10))
        when(mockedDeep.last) thenReturn Some(9)
        val digit: IDigit[ITreeComponent[Int]] =
          Digit4(deep, deep, deep, mockedDeep)

        digit.last should be(Some(9))
      }

      "return Node3 when 4 Node3 are stored" in {
        val digit: IDigit[INode[Int]] =
          Digit4(mockedNode, mockedNode, mockedNode, mockedNode)

        // @TODO was soll hier passieren? => Maybe none ausgeben

        digit.last should be(Some(mockedNode))
      }
    }

    "getting init" should {
      "return Some( Digit3 )" in {
        digit.init should be(Some(Digit3(10, 9, 8)))
      }
    }

    "getting tail" should {
      "return Some( Digit3 )" in {
        digit.tail should be(Some(Digit3(9, 8, 7)))
      }
    }

    "calling toList" should {
      "return List with 4 Elements" in {
        digit.toList should be(List(10, 9, 8, 7))
      }
    }

    "calling toTreeComponent" should {
      "return Deep( Digit2 Empty Digit2 )" in {
        digit.toTreeComponent should be(
          Deep(Digit2(10, 9), Empty(), Digit2(8, 7))
        )
      }
    }

    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10, 9, 8, 7 )")
      }
    }
  }
}
