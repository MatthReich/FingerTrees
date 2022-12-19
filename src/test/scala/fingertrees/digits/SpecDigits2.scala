package fingertrees.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit2
import digit.implDigit.Digit3
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

class SpecDigits2 extends AnyWordSpec with Matchers {

  "A Digit with 2 element" when {

    val digit: Digit2[Int] = Digit2(10, 9)
    val mockedEmpty = mock(classOf[Empty])
    val mockedDigit = mock(classOf[Digit1[Int]])
    val mockedNode = mock(classOf[Node3[Int]])
    val mockedDeep = mock(classOf[Deep[Int]])
    val mockedSingle = mock(classOf[Single[Int]])

    "appending a new element" should {
      "return a new Digit with 3 elements" in {
        val newDigit = digit.:+(8)

        newDigit should be(Digit3[Int](10, 9, 8))
      }
    }

    "prepending a new element" should {

      val digit: Digit2[Int] = Digit2(9, 10)

      "return a new Digit with 3 elements" in {
        val newDigit = digit.+:(8)

        newDigit should be(Digit3[Int](8, 9, 10))
      }
    }


    "checking size" should {
      "be 2 when 2 Values are stored" in {
        digit.size should be(2)
      }

      "be 0 when 2 Empty are stored" in {
        when(mockedEmpty.size) thenReturn 0
        val digit: IDigit[ITreeComponent[Nothing]] = Digit2(mockedEmpty, mockedEmpty)

        digit.size should be(0)
      }

      "be 2 when 2 Digit1 are stored" in {
        when(mockedDigit.size) thenReturn 1
        val single: IDigit[IDigit[Int]] = Digit2(mockedDigit, mockedDigit)

        single.size should be(2)
      }

      "be 3 when Deep( Digit1 Empty Digit1 ) and Single are stored" in {
        when(mockedDeep.size) thenReturn 2
        when(mockedSingle.size) thenReturn 1
        val single: IDigit[ITreeComponent[Int]] = Digit2(mockedDeep, mockedSingle)

        single.size should be(3)
      }

      "be 6 when 2 Node3 are stored" in {
        when(mockedNode.size) thenReturn 3
        val single: IDigit[INode[Int]] = Digit2(mockedNode, mockedNode)

        single.size should be(6)
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

      "return None when 2 Empty are stored" in {
        when(mockedEmpty.head) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] = Digit2(mockedEmpty, mockedEmpty)

        digit.head should be(None)
      }

      "return head of first Digit when 2 Digit1 are stored" in {
        when(mockedDigit.head) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] = Digit2(mockedDigit, mockedDigit)

        digit.head should be(Some(10))
      }

      "return head of Deep when Deep( Digit1 Empty Digit1 ) and Single are stored" in {
        when(mockedDeep.head) thenReturn Some(10)
        when(mockedSingle.head) thenReturn Some(9)
        val digit: IDigit[ITreeComponent[Int]] = Digit2(mockedDeep, mockedSingle)

        digit.head should be(Some(10))
      }

      "return Node3 when 2 Node3 are stored" in {
        val digit: IDigit[INode[Int]] = Digit2(Node3(10, 9, 8), Node3(7, 6, 5))

        // @TODO was soll hier passieren? => Maybe None zurück geben

        digit.head should be(Some(Node3(10, 9, 8)))
      }
    }

    "accessing last" should {
      "return own entry when only values are stored" in {
        digit.last should be(Some(9))
      }

      "return None when 2 Empty are stored" in {
        when(mockedEmpty.last) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] = Digit2(mockedEmpty, mockedEmpty)

        digit.last should be(None)
      }

      "return last of Digit when 2 Digit1 are stored" in {
        when(mockedDigit.last) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] = Digit2(Digit1(9), mockedDigit)

        digit.last should be(Some(10))
      }

      "return last of Deep when 2 Deep( Digit1 Empty Digit1 ) are stored" in {
        when(mockedDeep.last) thenReturn Some(9)
        val digit: IDigit[ITreeComponent[Int]] = Digit2(Deep(Digit1(11), Empty(), Digit1(10)), mockedDeep)

        digit.last should be(Some(9))
      }

      "return Node3 when Node3 is stored" in {
        val digit: IDigit[INode[Int]] = Digit2(Node3(10, 9, 8), Node3(7, 6, 5))

        // @TODO was soll hier passieren? => Maybe none ausgeben

        digit.last should be(Some(Node3(7, 6, 5)))
      }
    }

    "getting init" should {
      "return Some( Empty )" in {
        digit.init should be(Some(Digit1(10)))
      }
    }

    "getting tail" should {
      "return Some( Digit1 )" in {
        digit.tail should be(Some(Digit1(9)))
      }
    }

    "calling toList" should {
      "return List with 2 Elements" in {
        digit.toList should be(List(10, 9))
      }
    }

    "calling toTreeComponent" should {
      "return Single" in {
        digit.toTreeComponent should be(Deep(Digit1(10), Empty(), Digit1(9)))
      }
    }
  
    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10, 9 )")
      }
    }
  }
}
