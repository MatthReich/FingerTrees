package fingertree.implFingerTree.implTreeComponents.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit1
import digit.implDigit.Digit2
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import empty.implEmpty.Empty
import node.implNode.Node3
import deep.implDeep.Deep
import digit.IDigit
import fingertree.implFingerTree.ITreeComponent
import node.INode
import single.implSingle.Single

class SpecDigits1 extends AnyWordSpec with Matchers {

  "A Digit with 1 element" when {

    val digit: Digit1[Int] = Digit1(10)
    val mockedEmpty = mock(classOf[Empty])
    val mockedDigit = mock(classOf[Digit1[Int]])
    val mockedNode = mock(classOf[Node3[Int]])
    val mockedDeep = mock(classOf[Deep[Int]])

    "appending a new element" should {
      "return a new Digit with 2 elements" in {
        val newDigit = digit.:+(9)

        newDigit should be(Digit2[Int](10, 9))
      }
    }

    "prepending a new element" should {
      "return a new Digit with 2 elements" in {
        val newDigit = digit.+:(9)

        newDigit should be(Digit2[Int](9, 10))
      }
    }

    "checking size" should {
      "be 1 when a Value is stored" in {
        digit.size should be(1)
      }

      "be 0 when an Empty is stored" in {
        when(mockedEmpty.size) thenReturn 0
        val digit: IDigit[ITreeComponent[Nothing]] = Digit1(mockedEmpty)

        digit.size should be(0)
      }

      "be 1 when Digit1 is stored" in {
        when(mockedDigit.size) thenReturn 1
        val single: IDigit[IDigit[Int]] = Digit1(mockedDigit)

        single.size should be(1)
      }

      "be 2 when Deep( Digit1 Empty Digit1 ) is stored" in {
        when(mockedDeep.size) thenReturn 2
        val single: IDigit[ITreeComponent[Int]] = Digit1(mockedDeep)

        single.size should be(2)
      }

      "be 3 when Node3 is stored" in {
        when(mockedNode.size) thenReturn 3
        val single: IDigit[INode[Int]] = Digit1(mockedNode)

        single.size should be(3)
      }
    }

    "checking if its empty" should {
      "be false" in {
        digit.isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return own entry when a value is stored" in {
        digit.head should be(Some(10))
      }

      "return None when Empty is stored" in {
        when(mockedEmpty.head) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] = Digit1(mockedEmpty)

        digit.head should be(None)
      }

      "return head of Digit when Digit1 is stored" in {
        when(mockedDigit.head) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] = Digit1(mockedDigit)

        digit.head should be(Some(10))
      }

      "return head of Deep when Deep( Digit1 Empty Digit1 ) is stored" in {
        when(mockedDeep.head) thenReturn Some(10)
        val digit: IDigit[ITreeComponent[Int]] = Digit1(mockedDeep)

        digit.head should be(Some(10))
      }

      "return Node3 when Node3 is stored" in {
        val digit: IDigit[INode[Int]] = Digit1(Node3(10, 9, 8))

        // @TODO was soll hier passieren? => Maybe None zurück geben

        digit.head should be(Some(Node3(10, 9, 8)))
      }
    }

    "accessing last" should {
      "return own entry when a value is stored" in {
        digit.last should be(Some(10))
      }

      "return None when Empty is stored" in {
        when(mockedEmpty.last) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] = Digit1(mockedEmpty)

        digit.last should be(None)
      }

      "return last of Digit when Digit1 is stored" in {
        when(mockedDigit.last) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] = Digit1(mockedDigit)

        digit.last should be(Some(10))
      }

      "return last of Deep when Deep( Digit1 Empty Digit1 ) is stored" in {
        when(mockedDeep.last) thenReturn Some(9)
        val digit: IDigit[ITreeComponent[Int]] = Digit1(mockedDeep)

        digit.last should be(Some(9))
      }

      "return Node3 when Node3 is stored" in {
        val digit: IDigit[INode[Int]] = Digit1(Node3(10, 9, 8))

        // @TODO was soll hier passieren? => Maybe none ausgeben

        digit.last should be(Some(Node3(10, 9, 8)))
      }
    }

    "getting init" should {
      "return None" in {
        digit.init should be(None)
      }
    }

    "getting tail" should {
      "return None" in {
        digit.tail should be(None)
      }
    }

    "calling toList" should {
      "return List with 1 Element" in {
        digit.toList should be(List(10))
      }
    }

    "calling toTreeComponent" should {
      "return Single" in {
        digit.toTreeComponent should be(Single(10))
      }
    }

    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10 )")
      }
    }
  }
}
