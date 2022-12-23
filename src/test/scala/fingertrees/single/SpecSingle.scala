package fingertrees.single

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.implSingle.Single
import deep.implDeep.Deep
import digit.implDigit.Digit1
import empty.implEmpty.Empty
import single.ISingle
import empty.IEmpty
import digit.implDigit.Digit2
import deep.IDeep
import digit.IDigit
import node.INode
import node.implNode.Node3
import view.implView.implViewRightCons.ViewRightCons
import view.implView.implViewLeftCons.ViewLeftCons
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import fingertree.implFingerTree.ITreeComponent
import node.nodeImpl.Node2

class SpecSingle extends AnyWordSpec with Matchers {

  "A Single" when {

    val single: ISingle[Int] = Single[Int](10)
    val mockedEmpty = mock(classOf[Empty])
    val mockedDigit = mock(classOf[Digit1[Int]])
    val mockedNode = mock(classOf[Node3[Int]])
    val mockedDeep = mock(classOf[Deep[Int]])

    "appending a new element" should {
      "return a Deep( Digit1 Empty Digit1 ) structure where the right Digit is the new entry" in {
        val newSingle = single.:+(9)

        newSingle should be(Deep[Int](Digit1(10), Empty(), Digit1(9)))
      }
    }

    "prepending a new element" should {
      "return a Deep( Digit1 Empty Digit1 ) structure where the left Digit is the new entry" in {
        val newSingle = single.+:(9)

        newSingle should be(Deep[Int](Digit1(9), Empty(), Digit1(10)))
      }
    }

    "concating TreeComponents" should {
      "return a Single when concating an Empty" in {
        val emptyToConcat: IEmpty = Empty()

        val concated = single ++ emptyToConcat

        concated should be(Single(10))
      }

      "return a Deep( Digit1 Empty Digit1 ) when concating a Single where the left Digit is the old Single entry" in {
        val singleToConcat: ISingle[Int] = Single[Int](9)

        val concated = single ++ singleToConcat

        concated should be(Deep(Digit1(10), Empty(), Digit1(9)))
      }

      "return a Deep( Digit2 Empty Digit1 ) when concating a Deep where Digit2 has as first entry the old singe entry" in {
        val deepToConcat: IDeep[Int] = Deep(Digit1(9), Empty(), Digit1(8))

        val concated = single ++ deepToConcat

        concated should be(Deep(Digit2(10, 9), Empty(), Digit1(8)))
      }
    }

    "checking size" should {
      "be 1 when a Value is stored" in {
        single.size should be(1)
      }

      "be 0 when an Empty is stored" in {
        when(mockedEmpty.size) thenReturn 0
        val single: ISingle[ITreeComponent[Nothing]] = Single(mockedEmpty)

        single.size should be(0)
      }

      "be 1 when Digit1 is stored" in {
        when(mockedDigit.size) thenReturn 1
        val single: ISingle[IDigit[Int]] = Single(mockedDigit)

        single.size should be(1)
      }

      "be 2 when Deep( Digit1 Empty Digit1 ) is stored" in {
        when(mockedDeep.size) thenReturn 2
        val single: ISingle[ITreeComponent[Int]] = Single(mockedDeep)

        single.size should be(2)
      }

      "be 3 when Node3 is stored" in {
        when(mockedNode.size) thenReturn 3
        val single: ISingle[INode[Int]] = Single(mockedNode)

        single.size should be(3)
      }
    }

    "checking if its empty" should {
      "be false" in {
        single.isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return own entry when a value is stored" in {
        single.head should be(Some(10))
      }

      "return None when Empty is stored" in {
        when(mockedEmpty.head) thenReturn None
        val single: ISingle[IEmpty] = Single(mockedEmpty)

        single.head should be(None)
      }

      "return head of Digit when Digit1 is stored" in {
        when(mockedDigit.head) thenReturn Some(10)
        val single: ISingle[IDigit[Int]] = Single(mockedDigit)

        single.head should be(Some(10))
      }

      "return head of Deep when Deep( Digit1 Empty Digit1 ) is stored" in {
        when(mockedDeep.head) thenReturn Some(10)
        val single: ISingle[IDeep[Int]] = Single(mockedDeep)

        single.head should be(Some(10))
      }

      "return Node3 when Node3 is stored" in {
        val single: ISingle[INode[Int]] = Single(Node3(10, 9, 8))

        // @TODO was soll hier passieren? => Maybe None zurück geben

        single.head should be(Some(Node3(10, 9, 8)))
      }
    }

    "accessing last" should {
      "return own entry when a value is stored" in {
        single.last should be(Some(10))
      }

      "return None when Empty is stored" in {
        when(mockedEmpty.last) thenReturn None
        val single: ISingle[IEmpty] = Single(mockedEmpty)

        single.last should be(None)
      }

      "return last of Digit when Digit1 is stored" in {
        when(mockedDigit.last) thenReturn Some(10)
        val single: ISingle[IDigit[Int]] = Single(mockedDigit)

        single.last should be(Some(10))
      }

      "return last of Deep when Deep( Digit1 Empty Digit1 ) is stored" in {
        when(mockedDeep.last) thenReturn Some(9)
        val single: ISingle[IDeep[Int]] = Single(mockedDeep)

        single.last should be(Some(9))
      }

      "return Node3 when Node3 is stored" in {
        val single: ISingle[INode[Int]] = Single(Node3(10, 9, 8))

        // @TODO was soll hier passieren? => Maybe none ausgeben

        single.head should be(Some(Node3(10, 9, 8)))
      }
    }

    "getting init" should {
      "return Some( Empty )" in {
        single.init should be(Some(Empty()))
      }
    }

    "getting tail" should {
      "return Some( Empty )" in {
        single.tail should be(Some(Empty()))
      }
    }

    "calling toList" should {
      "return element as list" in {
        single.toList should be(List(10))
      }
    }

    "calling toString" should {
      "be presented right" in {
        single.toString should be("Single( 10 )")
      }
    }

    "viewRight" should {
      "return the own entry as last and empty as init" in {
        single.viewRight should be(Some(ViewRightCons(10, Empty())))
      }
    }

    "viewLeft" should {
      "return the own entry as head and empty as tail" in {
        single.viewLeft should be(Some(ViewLeftCons(10, Empty())))
      }
    }
  }
}
