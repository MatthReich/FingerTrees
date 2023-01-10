package fingertree.implFingerTree.implTreeComponents.digits

import deep.IDeep
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
          val res = digit :+ 5
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
          val res = 5 +: digit
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
      "be 0 when 4 Empty are stored" in {
        when(mockedEmpty.size) thenReturn 0
        val digit: IDigit[ITreeComponent[Nothing]] =
          Digit4(mockedEmpty, mockedEmpty, mockedEmpty, mockedEmpty)

        val size: Int = digit.size

        size should be(0)
      }

      "be 4 when 4 Values are stored" in {
        val size: Int = digit.size

        size should be(4)
      }

      "be 4 when 4 Digit1 are stored" in {
        when(mockedDigit.size) thenReturn 1
        val digit: IDigit[IDigit[Int]] =
          Digit4(mockedDigit, mockedDigit, mockedDigit, mockedDigit)

        val size: Int = digit.size

        size should be(4)
      }

      "be 6 when 2 Deep( Digit1 Empty Digit1 ) and 2 Single are stored" in {
        when(mockedDeep.size) thenReturn 2
        when(mockedSingle.size) thenReturn 1
        val digit: IDigit[ITreeComponent[Int]] =
          Digit4(mockedSingle, mockedDeep, mockedSingle, mockedDeep)

        val size: Int = digit.size

        size should be(6)
      }

      "be 12 when 4 Node3 are stored" in {
        when(mockedNode.size) thenReturn 3
        val digit: IDigit[INode[Int]] =
          Digit4(mockedNode, mockedNode, mockedNode, mockedNode)

        val size: Int = digit.size

        size should be(12)
      }
    }

    "checking if its empty" should {
      "be false" in {
        val isEmpty: Boolean = digit.isEmpty

        isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return first element which is stored" in {
        val head: Option[Int] = digit.head

        head match
          case None => fail("Head was None instead of Some")
          case Some(head) =>
            head should be(10)
      }
    }

    "accessing last" should {
      "return last element which is stored" in {
        val last: Option[Int] = digit.last

        last match
          case None => fail("Head was None instead of Some")
          case Some(last) =>
            last should be(7)
      }
    }

    "getting init" should {
      "return Some( Digit3 )" in {
        val init: Option[IDigit[Int]] = digit.init

        init match
          case None => fail("Init was None instead of Some")
          case Some(init) =>
            init should be(Digit3(10, 9, 8))
      }
    }

    "getting tail" should {
      "return Some( Digit3 )" in {
        val tail: Option[IDigit[Int]] = digit.tail

        tail match
          case None => fail("Tail was None instead of Some")
          case Some(tail) =>
            tail should be(Digit3(9, 8, 7))
      }
    }

    "calling toList" should {
      "return List with 4 Elements" in {
        val list: List[Int] = digit.toList

        val expectedList: List[Int] = List(10, 9, 8, 7)
        list should be(expectedList)
      }
    }

    "calling toTreeComponent" should {
      "return Deep( Digit2 Empty Digit2 )" in {
        val treeComponent: ITreeComponent[Int] = digit.toTreeComponent

        val expectedTreeComponent: ITreeComponent[Int] =
          Deep(Digit2(10, 9), Empty(), Digit2(8, 7))
        treeComponent should be(expectedTreeComponent)
      }
    }

    "calling toString" should {
      "be presented right" in {
        val stringRepresentation: String = digit.toString

        val expectedString: String = "Digit( 10, 9, 8, 7 )"
        stringRepresentation should be(expectedString)
      }
    }
  }
}
