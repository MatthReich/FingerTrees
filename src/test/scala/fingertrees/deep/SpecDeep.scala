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

class SpecDeep extends AnyWordSpec with Matchers {

  "A Deep" when {

    val deep: Deep[Int] = Deep(Digit1(10), Empty(), Digit1(9))

    "adding a new element" should {
      "return a Deep with 2 Digits on right when 1 Digit before" in {

        val newDeep = deep.+:(8)

        newDeep should be(Deep[Int](Digit1(10), Empty(), Digit2(9, 8)))
      }
      "return a Deep with 3 Digits on right when 2 Digits before" in {

        val newDeep = deep.+:(8).+:(7)

        newDeep should be(Deep[Int](Digit1(10), Empty(), Digit3(9, 8, 7)))
      }
      "return a Deep with 4 Digits on right when 3 Digits before" in {

        val newDeep = deep.+:(8).+:(7).+:(6)

        newDeep should be(Deep[Int](Digit1(10), Empty(), Digit4(9, 8, 7, 6)))
      }
    }
    "calling toString" should {
      "be presented right" in {
        deep.toString should be("Deep( Digit( 10 ), Empty(), Digit( 9 ) )")
      }
    }
  }
}
