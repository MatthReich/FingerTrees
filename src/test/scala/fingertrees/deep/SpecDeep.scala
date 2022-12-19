package fingertrees.deep

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.implSingle.Single
import deep.implDeep.Deep
import digit.implDigit.Digit1
import empty.implEmpty.Empty
import digit.implDigit.Digit2
import digit.implDigit.Digit3
import digit.implDigit.Digit4
import node.implNode.Node3
import empty.IEmpty
import single.ISingle
import deep.IDeep
import node.nodeImpl.Node2
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import view.implView.implViewLeftCons.ViewLeftCons
import view.implView.implViewRightCons.ViewRightCons

class SpecDeep extends AnyWordSpec with Matchers {

  "A Deep" when {

    val deep: IDeep[Int] = Deep(Digit1(10), Empty(), Digit1(9))
    val mockedDigit = mock(classOf[Digit1[Int]])
    val mockedEmpty = mock(classOf[Empty])
    val mockedSingle = mock(classOf[Single[Node3[Int]]])

    "appending a new element" should {
      "return a Deep with 2 Digits on right when 1 Digit before" in {
        val newDeep = deep.:+(8)

        newDeep should be(Deep[Int](Digit1(10), Empty(), Digit2(9, 8)))
      }

      "return a Deep with 3 Digits on right when 2 Digits before" in {
        val newDeep = deep.:+(8).:+(7)

        newDeep should be(Deep[Int](Digit1(10), Empty(), Digit3(9, 8, 7)))
      }

      "return a Deep with 4 Digits on right when 3 Digits before" in {
        val newDeep = deep.:+(8).:+(7).:+(6)

        newDeep should be(Deep[Int](Digit1(10), Empty(), Digit4(9, 8, 7, 6)))
      }

      "return a Deep with Digit1 as prefix, Digit2 as suffix and as tree a Single with Node3" in {
        val newDeep = deep.:+(8).:+(7).:+(6).:+(5)

        newDeep should be(
          Deep[Int](Digit1(10), Single(Node3(9, 8, 7)), Digit2(6, 5))
        )
      }
    }

    "prepending a new element" should {

      val deep: Deep[Int] = Deep(Digit1(9), Empty(), Digit1(10))

      "return a Deep( Digit2 Empty Digit1 ) structure after prepending 1 element" in {
        val newDeep = deep.+:(8)

        newDeep should be(Deep[Int](Digit2(8, 9), Empty(), Digit1(10)))
      }

      "return a Deep( Digit2 Single(Node3) Digit1 ) structure after prepending to Digit4" in {
        val newDeep = deep.+:(8).+:(7).+:(6).+:(5)

        newDeep should be(
          Deep[Int](Digit2(5, 6), Single(Node3(7, 8, 9)), Digit1(10))
        )
      }
    }

    "concating TreeComponents" should {
      "return a itself when concating an Empty" in {
        val emptyToConcat: IEmpty = Empty()

        val concated = deep ++ emptyToConcat

        concated should be(deep)
      }

      "return a Deep( Digit1 Empty Digit2 ) when concating a Single where the right Digit contains the new entry on last position" in {
        val singleToConcat: ISingle[Int] = Single[Int](8)

        val concated = deep ++ singleToConcat

        concated should be(Deep(Digit1(10), Empty(), Digit2(9, 8)))
      }

      "return a Deep( Digit1 Single( Node2 ) Digit1 ) when concating a Deep where Digit2 has as first entry the old singe entry" in {
        val deepToConcat: IDeep[Int] = Deep(Digit1(8), Empty(), Digit1(7))

        val concated = deep ++ deepToConcat

        concated should be(Deep(Digit1(10), Single(Node2(9, 8)), Digit1(7)))
      }
    }

    "checking size" should {
      "be 2 when only 2 Digits and 1 Empty" in {
        when(mockedDigit.size) thenReturn 1
        when(mockedEmpty.size) thenReturn 0
        val deep: IDeep[Int] = Deep(mockedDigit, mockedEmpty, mockedDigit)

        deep.size should be(2)
      }

      "be 5 when Deep( Digit1 Single( Node3 ) Digit1 )" in {
        when(mockedDigit.size) thenReturn 1
        when(mockedSingle.size) thenReturn 3
        val deep: IDeep[Int] = Deep(mockedDigit, mockedSingle, mockedDigit)

        deep.size should be(5)
      }
    }

    "checking if its empty" should {
      "be false" in {
        deep.isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return right head element" in {
        when(mockedDigit.head) thenReturn Some(10)
        val deep: IDeep[Int] = Deep(mockedDigit, Empty(), Digit1(9))

        deep.head should be(Some(10))
      }
    }

    "accessing last" should {
      "return right last element" in {
        when(mockedDigit.last) thenReturn Some(10)
        val deep: IDeep[Int] = Deep(Digit1(9), Empty(), mockedDigit)

        deep.last should be(Some(10))
      }
    }

    "calling toString" should {
      "be presented right" in {
        deep.toString should be("Deep( Digit( 10 ), Empty(), Digit( 9 ) )")
      }
    }

    "viewRight" should {
      "return None when no head of prefix exists" in {
        when(mockedDigit.last) thenReturn None
        val deep: IDeep[Int] = Deep(mockedDigit, Empty(), mockedDigit)
  
        deep.viewRight should be(None)
      }
    }

    "viewLeft" should {
      "return None when no head of prefix exists" in {
        when(mockedDigit.head) thenReturn None
        val deep: IDeep[Int] = Deep(mockedDigit, Empty(), mockedDigit)
  
        deep.viewLeft should be(None)
      }

      "return head of prefix and tail of suffix when tail is None" in {
        when(mockedDigit.head) thenReturn Some(10)
        when(mockedDigit.tail) thenReturn None
        when(mockedDigit.toTreeComponent) thenReturn Single(9)
        val deep = Deep(mockedDigit, Empty(), mockedDigit)
  
        deep.viewLeft should be(Some(ViewLeftCons(10, Single(9))))
      }

      "return head of prefix and tail of suffix when tail is Some" in {
        when(mockedDigit.head) thenReturn Some(10)
        when(mockedDigit.tail) thenReturn Some(Digit1(10))
        when(mockedDigit.toTreeComponent) thenReturn Single(9)
        val deep = Deep(mockedDigit, Empty(), mockedDigit)
  
        deep.viewLeft should be(Some(ViewLeftCons(10, Deep(Digit1(10), Empty(), mockedDigit))))
      }
    }
  }
}
