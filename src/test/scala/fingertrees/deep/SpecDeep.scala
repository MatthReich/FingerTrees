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

class SpecDeep extends AnyWordSpec with Matchers {

  "A Deep" when {

    val deep: Deep[Int] = Deep(Digit1(10), Empty(), Digit1(9))

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

    "checking size" should {
      "be 2 when only 2 Digits and 1 Empty" in {
        deep.size should be(2)
      }

      "be 6 when 1 Digit left, 1 Single with Node3 middle and 2 Digits right" in {
        val newDeep = deep.:+(8).:+(7).:+(6).:+(5)

        newDeep.size should be(6)
      }
    }

    "checking if its empty" should {
      "be false" in {
        deep.isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return right head element" in {
        deep.head should be(Some(10))
      }
    }

    "accessing last" should {
      "return right last element" in {
        deep.last should be(Some(9))
      }
    }

    "calling toString" should {
      "be presented right" in {
        deep.toString should be("Deep( Digit( 10 ), Empty(), Digit( 9 ) )")
      }
    }
  }
}
