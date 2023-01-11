package fingertree.implFingerTree.implTreeComponents.single

import deep.IDeep
import deep.implDeep.Deep
import digit.IDigit
import digit.implDigit.Digit1
import digit.implDigit.Digit2
import empty.IEmpty
import empty.implEmpty.Empty
import fingertree.IFingerTree
import fingertree.implFingerTree.ITreeComponent
import node.INode
import node.implNode.Node3
import node.nodeImpl.Node2
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.ISingle
import single.implSingle.Single
import view.implView.IViewLeft
import view.implView.IViewRight
import view.implView.implViewLeftCons.ViewLeftCons
import view.implView.implViewRightCons.ViewRightCons

class SpecSingle extends AnyWordSpec with Matchers {

  "A Single" when {

    val single: ISingle[Int] = Single[Int](10)
    val mockedEmpty = mock(classOf[Empty])
    val mockedDigit = mock(classOf[Digit1[Int]])
    val mockedNode = mock(classOf[Node3[Int]])
    val mockedDeep = mock(classOf[Deep[Int]])

    "appending a new element" should {
      "return a Deep( Digit1 Empty Digit1 ) structure where the right Digit is the new entry" in {
        val newSingle: ITreeComponent[Int] = single :+ 9

        val expectedDeep: IDeep[Int] = Deep[Int](Digit1(10), Empty(), Digit1(9))
        newSingle should be(expectedDeep)
      }
    }

    "prepending a new element" should {
      "return a Deep( Digit1 Empty Digit1 ) structure where the left Digit is the new entry" in {
        val newSingle: ITreeComponent[Int] = 9 +: single

        val expectedDeep: IDeep[Int] = Deep[Int](Digit1(9), Empty(), Digit1(10))
        newSingle should be(expectedDeep)
      }
    }

    "concatenate TreeComponents" should {
      "return a Single when concatenate an Empty" in {
        val emptyToConcat: IEmpty = Empty()

        val concatenated = single ++ emptyToConcat

        val expectedSingle: ISingle[Int] = Single(10)
        concatenated should be(expectedSingle)
      }

      "return a Deep( Digit1 Empty Digit1 ) when concatenate a Single where the left Digit is the old Single entry" in {
        val singleToConcat: ISingle[Int] = Single[Int](9)

        val concatenated = single ++ singleToConcat

        val expectedDeep: IDeep[Int] = Deep(Digit1(10), Empty(), Digit1(9))
        concatenated should be(expectedDeep)
      }

      "return a Deep( Digit2 Empty Digit1 ) when concatenate a Deep where Digit2 has as first entry the old singe entry" in {
        val deepToConcat: IDeep[Int] = Deep(Digit1(9), Empty(), Digit1(8))

        val concatenated = single ++ deepToConcat

        val expectedDeep: IDeep[Int] = Deep(Digit2(10, 9), Empty(), Digit1(8))
        concatenated should be(expectedDeep)
      }
    }

    "checking size" should {
      "be 0 when an Empty is stored" in {
        when(mockedEmpty.size) thenReturn 0
        val single: ISingle[ITreeComponent[Nothing]] = Single(mockedEmpty)

        val size: Int = single.size

        size should be(0)
      }

      "be 1 when a Value is stored" in {
        val size: Int = single.size

        size should be(1)
      }

      "be 1 when Digit1 is stored" in {
        when(mockedDigit.size) thenReturn 1
        val single: ISingle[IDigit[Int]] = Single(mockedDigit)

        val size: Int = single.size

        size should be(1)
      }

      "be 2 when Deep( Digit1 Empty Digit1 ) is stored" in {
        when(mockedDeep.size) thenReturn 2
        val single: ISingle[ITreeComponent[Int]] = Single(mockedDeep)

        val size: Int = single.size

        size should be(2)
      }

      "be 3 when Node3 is stored" in {
        when(mockedNode.size) thenReturn 3
        val single: ISingle[INode[Int]] = Single(mockedNode)

        val size: Int = single.size

        size should be(3)
      }
    }

    "checking if its empty" should {
      "be false" in {
        val isEmpty: Boolean = single.isEmpty

        isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return own entry which is stored" in {
        val head: Option[Int] = single.head

        head match
          case None => fail("Head was None instead of Some")
          case Some(head) =>
            head should be(10)
      }
    }

    "accessing last" should {
      "return own entry which is stored" in {
        val last: Option[Int] = single.last

        last match
          case None => fail("Last was None instead of Some")
          case Some(last) =>
            last should be(10)
      }
    }

    "getting init" should {
      "return Some( Empty )" in {
        val init: Option[ITreeComponent[Int]] = single.init

        init match
          case None => fail("Init was None instead of Some")
          case Some(init) =>
            init should be(Empty())
      }
    }

    "getting tail" should {
      "return Some( Empty )" in {
        val tail: Option[ITreeComponent[Int]] = single.tail

        tail match
          case None => fail("Tail was None instead of Some")
          case Some(tail) =>
            tail should be(Empty())
      }
    }

    "calling toList" should {
      "return element as list" in {
        val list: List[Int] = single.toList

        val expectedList: List[Int] = List(10)
        list should be(expectedList)
      }
    }

    "calling toString" should {
      "be presented right" in {
        val stringRepresentation: String = single.toString

        val expectedString: String = "Single( 10 )"
        stringRepresentation should be(expectedString)
      }
    }

    "viewRight" should {
      "return the own entry as last and empty as init" in {
        val viewRight: Option[IViewRight[Int]] = single.viewRight

        viewRight match
          case None => fail("ViewRight was None instead of Some")
          case Some(viewRight) =>
            val expectedViewRight: IViewRight[Int] = ViewRightCons(10, Empty())
            viewRight should be(expectedViewRight)
      }
    }

    "viewLeft" should {
      "return the own entry as head and empty as tail" in {
        val viewLeft: Option[IViewLeft[Int]] = single.viewLeft

        viewLeft match
          case None => fail("ViewLeft was None instead of Some")
          case Some(viewLeft) =>
            val expectedViewLeft: IViewLeft[Int] = ViewLeftCons(10, Empty())
            viewLeft should be(expectedViewLeft)
      }
    }
  }
}
