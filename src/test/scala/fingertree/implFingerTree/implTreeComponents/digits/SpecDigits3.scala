package fingertree.implFingerTree.implTreeComponents.digits

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
        val newDigit = digit :+ 7

        val expectedDigit: IDigit[Int] = Digit4[Int](10, 9, 8, 7)
        newDigit should be(expectedDigit)
      }
    }

    "prepending a new element" should {

      val digit: Digit3[Int] = Digit3(8, 9, 10)

      "return a new Digit with 2 elements" in {
        val newDigit = 7 +: digit

        val expectedDigit: IDigit[Int] = Digit4[Int](7, 8, 9, 10)
        newDigit should be(expectedDigit)
      }
    }

    "checking size" should {
      "be 0 when 3 Empty are stored" in {
        when(mockedEmpty.size) thenReturn 0
        val digit: IDigit[ITreeComponent[Nothing]] =
          Digit3(mockedEmpty, mockedEmpty, mockedEmpty)

        val size: Int = digit.size

        size should be(0)
      }

      "be 3 when 3 Values are stored" in {
        val size: Int = digit.size

        size should be(3)
      }

      "be 3 when 3 Digit1 are stored" in {
        when(mockedDigit.size) thenReturn 1
        val digit: IDigit[IDigit[Int]] =
          Digit3(mockedDigit, mockedDigit, mockedDigit)

        val size: Int = digit.size

        size should be(3)
      }

      "be 4 when Deep( Digit1 Empty Digit1 ) and 2 Single are stored" in {
        when(mockedDeep.size) thenReturn 2
        when(mockedSingle.size) thenReturn 1
        val digit: IDigit[ITreeComponent[Int]] =
          Digit3(mockedSingle, mockedDeep, mockedSingle)

        val size: Int = digit.size

        size should be(4)
      }

      "be 9 when 3 Node3 are stored" in {
        when(mockedNode.size) thenReturn 3
        val digit: IDigit[INode[Int]] =
          Digit3(mockedNode, mockedNode, mockedNode)

        val size: Int = digit.size

        size should be(9)
      }
    }

    "checking if its empty" should {
      "be false" in {
        val isEmpty = digit.isEmpty

        isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return own head when values are stored" in {
        val head: Option[Int] = digit.head

        head match
          case None => fail("Head was None instead of Some")
          case Some(head) =>
            head should be(10)
      }

      "return None when 3 Empty are stored" in {
        when(mockedEmpty.head) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] =
          Digit3(mockedEmpty, mockedEmpty, mockedEmpty)

        val head: Option[_] = digit.head

        head should be(None)
      }

      "return head of first Digit when 3 Digit1 are stored" in {
        when(mockedDigit.head) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] =
          Digit3(mockedDigit, Digit1(9), Digit1(9))

        val head: Option[_] = digit.head

        head match
          case None => fail("Head was None instead of Some")
          case Some(head) =>
            head should be(10)
      }

      "return head of Deep when Deep( Digit1 Empty Digit1 ) and 2 Single are stored" in {
        when(mockedDeep.head) thenReturn Some(10)
        when(mockedSingle.head) thenReturn Some(9)
        val digit: IDigit[ITreeComponent[Int]] =
          Digit3(mockedDeep, mockedSingle, mockedSingle)

        val head: Option[_] = digit.head

        head match
          case None => fail("Head was None instead of Some")
          case Some(head) =>
            head should be(10)
      }
    }

    "accessing last" should {
      "return None when 3 Empty are stored" in {
        when(mockedEmpty.last) thenReturn None
        val digit: IDigit[ITreeComponent[Int]] =
          Digit3(mockedEmpty, mockedEmpty, mockedEmpty)

        val last: Option[_] = digit.last

        last should be(None)
      }

      "return own entry when only values are stored" in {
        val last: Option[Int] = digit.last

        last match
          case None => fail("Last was None instead of Some")
          case Some(last) =>
            last should be(8)
      }

      "return last of Digit when 3 Digit1 are stored" in {
        when(mockedDigit.last) thenReturn Some(10)
        val digit: IDigit[IDigit[Int]] =
          Digit3(Digit1(9), Digit1(8), mockedDigit)

        val last: Option[_] = digit.last

        last match
          case None => fail("Last was None instead of Some")
          case Some(last) =>
            last should be(10)
      }

      "return last of Deep when 3 Deep( Digit1 Empty Digit1 ) are stored" in {
        when(mockedDeep.last) thenReturn Some(9)
        val digit: IDigit[ITreeComponent[Int]] = Digit3(
          Deep(Digit1(11), Empty(), Digit1(10)),
          Deep(Digit1(5), Empty(), Digit1(4)),
          mockedDeep
        )

        val last: Option[_] = digit.last

        last match
          case None => fail("Last was None instead of Some")
          case Some(last) =>
            last should be(9)
      }
    }

    "getting init" should {
      "return Some( Digit2 )" in {
        val init: Option[IDigit[Int]] = digit.init

        init match
          case None => fail("Init was None instead of Some")
          case Some(init) =>
            init should be(Digit2(10, 9))
      }
    }

    "getting tail" should {
      "return Some( Digit2 )" in {
        val tail: Option[IDigit[Int]] = digit.tail

        tail match
          case None => fail("Tail was None instead of Some")
          case Some(tail) =>
            tail should be(Digit2(9, 8))
      }
    }

    "calling toList" should {
      "return List with 3 Elements" in {
        val list: List[Int] = digit.toList

        val expectedList: List[Int] = List(10, 9, 8)
        list should be(expectedList)
      }
    }

    "calling toTreeComponent" should {
      "return Deep ( Digit2 Empty Digit1 )" in {
        val treeComponent: ITreeComponent[Int] = digit.toTreeComponent

        val expectedTreeComponent: ITreeComponent[Int] =
          Deep(Digit2(10, 9), Empty(), Digit1(8))
        digit.toTreeComponent should be(expectedTreeComponent)
      }
    }

    "calling toString" should {
      "be presented right" in {
        val stringRepresentation: String = digit.toString

        val expectedString: String = "Digit( 10, 9, 8 )"
        stringRepresentation should be(expectedString)
      }
    }
  }
}
