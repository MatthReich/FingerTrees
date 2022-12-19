package fingertrees.digits

import deep.implDeep.Deep
import digit.IDigit
import digit.implDigit.Digit1
import digit.implDigit.Digit2
import digit.implDigit.Digit3
import digit.implDigit.Digit4
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import node.INode
import node.implNode.Node3
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.implSingle.Single

class SpecDigits3 extends AnyWordSpec with Matchers {

  "A Digit with 1 element" when {

    val digit: Digit3[Int] = Digit3(10, 9, 8)
    val mockedEmpty = mock(classOf[Empty])
    val mockedDigit = mock(classOf[Digit1[Int]])
    val mockedNode = mock(classOf[Node3[Int]])
    val mockedDeep = mock(classOf[Deep[Int]])
    val mockedSingle = mock(classOf[Single[Int]])

    "appending a new element" should {
      "return a new Digit with 2 elements" in {
        val newDigit = digit.:+(7)

        newDigit should be(Digit4[Int](10, 9, 8, 7))
      }
    }

    "prepending a new element" should {

      val digit: Digit3[Int] = Digit3(8, 9, 10)

      "return a new Digit with 2 elements" in {
        val newDigit = digit.+:(7)

        newDigit should be(Digit4[Int](7, 8, 9, 10))
      }
    }

    "checking size" should {
      "be 3 when 3 Values are stored" in {
        digit.size should be(3)
      }

      "be 0 when 3 Empty are stored" in {
        when(mockedEmpty.size) thenReturn 0
        val digit: IDigit[ITreeComponent[Nothing]] =
          Digit3(mockedEmpty, mockedEmpty, mockedEmpty)

        digit.size should be(0)
      }

      "be 3 when 3 Digit1 are stored" in {
        when(mockedDigit.size) thenReturn 1
        val digit: IDigit[IDigit[Int]] =
          Digit3(mockedDigit, mockedDigit, mockedDigit)

        digit.size should be(3)
      }

      "be 4 when Deep( Digit1 Empty Digit1 ) and 2 Single are stored" in {
        when(mockedDeep.size) thenReturn 2
        when(mockedSingle.size) thenReturn 1
        val digit: IDigit[ITreeComponent[Int]] =
          Digit3(mockedSingle, mockedDeep, mockedSingle)

        digit.size should be(4)
      }

      "be 9 when 3 Node3 are stored" in {
        when(mockedNode.size) thenReturn 3
        val digit: IDigit[INode[Int]] =
          Digit3(mockedNode, mockedNode, mockedNode)

        digit.size should be(9)
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

      "return None when 3 Empty are stored" in {
        when(mockedEmpty.head) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] =
          Digit3(mockedEmpty, mockedEmpty, mockedEmpty)

        digit.head should be(None)
      }

      "return head of first Digit when 3 Digit1 are stored" in {
        when(mockedDigit.head) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] =
          Digit3(mockedDigit, Digit1(9), Digit1(9))

        digit.head should be(Some(10))
      }

      "return head of Deep when Deep( Digit1 Empty Digit1 ) and 2 Single are stored" in {
        when(mockedDeep.head) thenReturn Some(10)
        when(mockedSingle.head) thenReturn Some(9)
        val digit: IDigit[ITreeComponent[Int]] =
          Digit3(mockedDeep, mockedSingle, mockedSingle)

        digit.head should be(Some(10))
      }

      "return Node3 when 2 Node3 are stored" in {
        val digit: IDigit[INode[Int]] =
          Digit3(mockedNode, Node3(7, 6, 5), Node3(4, 3, 2))

        // @TODO was soll hier passieren? => Maybe None zurück geben

        digit.head should be(Some(mockedNode))
      }
    }

    "accessing last" should {
      "return own entry when only values are stored" in {
        digit.last should be(Some(8))
      }

      "return None when 3 Empty are stored" in {
        when(mockedEmpty.last) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] =
          Digit3(mockedEmpty, mockedEmpty, mockedEmpty)

        digit.last should be(None)
      }

      "return last of Digit when 3 Digit1 are stored" in {
        when(mockedDigit.last) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] =
          Digit3(Digit1(9), Digit1(8), mockedDigit)

        digit.last should be(Some(10))
      }

      "return last of Deep when 3 Deep( Digit1 Empty Digit1 ) are stored" in {
        when(mockedDeep.last) thenReturn Some(9)
        val digit: IDigit[ITreeComponent[Int]] = Digit3(
          Deep(Digit1(11), Empty(), Digit1(10)),
          Deep(Digit1(5), Empty(), Digit1(4)),
          mockedDeep
        )

        digit.last should be(Some(9))
      }

      "return Node3 when 3 Node3 are stored" in {
        val digit: IDigit[INode[Int]] =
          Digit3(mockedNode, mockedNode, mockedNode)

        // @TODO was soll hier passieren? => Maybe none ausgeben

        digit.last should be(Some(mockedNode))
      }
    }

    "getting init" should {
      "return Some( Digit2 )" in {
        digit.init should be(Some(Digit2(10, 9)))
      }
    }

    "getting tail" should {
      "return Some( Digit2 )" in {
        digit.tail should be(Some(Digit2(9, 8)))
      }
    }

    "calling toList" should {
      "return List with 3 Elements" in {
        digit.toList should be(List(10, 9, 8))
      }
    }

    "calling toTreeComponent" should {
      "return Deep ( Digit2 Empty Digit1 )" in {
        digit.toTreeComponent should be(Deep(Digit2(10, 9), Empty(), Digit1(8)))
      }
    }

    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10, 9, 8 )")
      }
    }
  }
}
