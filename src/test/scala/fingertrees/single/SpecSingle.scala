package fingertrees.single

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.implSingle.Single
import deep.implDeep.Deep
import digit.implDigit.Digit1
import empty.implEmpty.Empty

class SpecSingle extends AnyWordSpec with Matchers {

  "A Single" when {

    val single: Single[Int] = Single(10)

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

    "checking size" should {
      "be 1 when only 1 Value is stored" in {
        single.size should be(1)
      }
    }

    "accessing head" should {
      "return right head element" in {
        single.head should be(Some(10))
      }
    }

    "accessing last" should {
      "return right last element" in {
        single.last should be(Some(10))
      }
    }

    "calling toString" should {
      "be presented right" in {
        single.toString should be("Single( 10 )")
      }
    }
  }
}
