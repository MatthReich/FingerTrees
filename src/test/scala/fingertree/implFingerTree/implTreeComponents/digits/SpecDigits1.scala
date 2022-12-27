package fingertree.implFingerTree.implTreeComponents.digits

import deep.implDeep.Deep
import digit.IDigit
import digit.implDigit.Digit1
import digit.implDigit.Digit2
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import node.INode
import node.implNode.Node3
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
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
        val newDigit = digit :+ 9

        val expectedDigit: IDigit[Int] = Digit2(10, 9)
        newDigit should be(expectedDigit)
      }
    }

    "prepending a new element" should {
      "return a new Digit with 2 elements" in {
        val newDigit = 9 +: digit

        val expectedDigit: IDigit[Int] = Digit2(9, 10)
        newDigit should be(expectedDigit)
      }
    }

    "checking size" should {
      "be 0 when an Empty is stored" in {
        when(mockedEmpty.size) thenReturn 0
        val digit: IDigit[ITreeComponent[Nothing]] = Digit1(mockedEmpty)

        val size: Int = digit.size

        size should be(0)
      }

      "be 1 when a Value is stored" in {
        val size: Int = digit.size

        size should be(1)
      }

      "be 1 when Digit1 is stored" in {
        when(mockedDigit.size) thenReturn 1
        val digit: IDigit[IDigit[Int]] = Digit1(mockedDigit)

        val size: Int = digit.size

        size should be(1)
      }

      "be 2 when Deep( Digit1 Empty Digit1 ) is stored" in {
        when(mockedDeep.size) thenReturn 2
        val digit: IDigit[ITreeComponent[Int]] = Digit1(mockedDeep)

        val size: Int = digit.size

        size should be(2)
      }

      "be 3 when Node3 is stored" in {
        when(mockedNode.size) thenReturn 3
        val digit: IDigit[INode[Int]] = Digit1(mockedNode)

        val size: Int = digit.size

        size should be(3)
      }
    }

    "checking if its empty" should {
      "be false" in {
        val isEmpty: Boolean = digit.isEmpty

        isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return own entry when a value is stored" in {
        val head: Option[Int] = digit.head

        head match
          case None => fail("Head was None instead of Some")
          case Some(head) =>
            head should be(10)
      }

      "return None when Empty is stored" in {
        when(mockedEmpty.head) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] = Digit1(mockedEmpty)

        val head: Option[ITreeComponent[Int]] = digit.head

        head should be(None)
      }

      "return head of Digit when Digit1 is stored" in {
        when(mockedDigit.head) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] = Digit1(mockedDigit)

        val head: Option[_] = digit.head

        head match
          case None => fail("Head was None instead of Some")
          case Some(head) =>
            head should be(10)
      }

      "return head of Deep when Deep( Digit1 Empty Digit1 ) is stored" in {
        when(mockedDeep.head) thenReturn Some(10)
        val digit: IDigit[ITreeComponent[Int]] = Digit1(mockedDeep)

        val head: Option[_] = digit.head

        head match
          case None => fail("Head was None instead of Some")
          case Some(head) =>
            head should be(10)
      }
    }

    "accessing last" should {
      "return own entry when a value is stored" in {
        val last: Option[Int] = digit.last

        last match
          case None => fail("Last was None instead of Some")
          case Some(last) =>
            last should be(10)
      }

      "return None when Empty is stored" in {
        when(mockedEmpty.last) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] = Digit1(mockedEmpty)

        val last: Option[_] = digit.last

        last should be(None)
      }

      "return last of Digit when Digit1 is stored" in {
        when(mockedDigit.last) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] = Digit1(mockedDigit)

        val last: Option[_] = digit.last

        last match
          case None => fail("Last was None instead of Some")
          case Some(last) =>
            last should be(10)
      }

      "return last of Deep when Deep( Digit1 Empty Digit1 ) is stored" in {
        when(mockedDeep.last) thenReturn Some(9)
        val digit: IDigit[ITreeComponent[Int]] = Digit1(mockedDeep)

        val last: Option[_] = digit.last

        last match
          case None => fail("Last was None instead of Some")
          case Some(last) =>
            last should be(9)
      }
    }

    "getting init" should {
      "return None" in {
        val init: Option[IDigit[Int]] = digit.init

        init should be(None)
      }
    }

    "getting tail" should {
      "return None" in {
        val tail: Option[IDigit[Int]] = digit.tail

        tail should be(None)
      }
    }

    "calling toList" should {
      "return List with 1 Element" in {
        val list: List[Int] = digit.toList

        val expectedList: List[Int] = List(10)
        list should be(expectedList)
      }
    }

    "calling toTreeComponent" should {
      "return Single" in {
        val treeComponent: ITreeComponent[Int] = digit.toTreeComponent

        val expectedTreeComponent: ITreeComponent[Int] = Single(10)
        treeComponent should be(expectedTreeComponent)
      }
    }

    "calling toString" should {
      "be presented right" in {
        val stringRepresentation: String = digit.toString

        val expectedString: String = "Digit( 10 )"
        stringRepresentation should be(expectedString)
      }
    }
  }
}
