package fingertrees.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit3
import digit.implDigit.Digit4

class SpecDigits3 extends AnyWordSpec with Matchers {

  "A Digit with 1 element" when {

    val digit: Digit3[Int] = Digit3(10, 9, 8)

    "appending a new element" should {
      "return a new Digit with 2 elements" in {
        val newDigit = digit.:+(7)

        newDigit should be(Digit4[Int](10, 9, 8, 7))
      }
    }

    "prepending a new element" should {

      val digit: Digit3[Int] = Digit3(8, 9, 10)

      "return a new Digit with 2 elements" in {
        val newDigit = digit.+:(7)

        newDigit should be(Digit4[Int](7, 8, 9, 10))
      }
    }

    "checking size" should {
      "be 3 when only 3 Value are stored" in {
        digit.size should be(3)
      }
    }

    "accessing head" should {
      "return right head element" in {
        digit.head should be(Some(10))
      }
    }

    "accessing last" should {
      "return right last element" in {
        digit.last should be(Some(8))
      }
    }
    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10, 9, 8 )")
      }
    }
  }
}
