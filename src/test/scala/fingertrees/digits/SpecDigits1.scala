package fingertrees.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit1
import digit.implDigit.Digit2

class SpecDigits1 extends AnyWordSpec with Matchers {

  "A Digit with 1 element" when {

    val digit: Digit1[Int] = Digit1(10)

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
      "be 1 when only 1 Value is stored" in {
        digit.size should be(1)
      }
    }

    "accessing head" should {
      "return right head element" in {
        digit.head should be(Some(10))
      }
    }

    "accessing last" should {
      "return right last element" in {
        digit.last should be(Some(10))
      }
    }

    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10 )")
      }
    }
  }
}
