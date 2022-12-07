package fingertrees.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit2
import digit.implDigit.Digit3

class SpecDigits2 extends AnyWordSpec with Matchers {

  "A Digit with 2 element" when {

    val digit: Digit2[Int] = Digit2(10, 9)

    "adding a new element" should {
      "return a new Digit with 3 elements" in {

        val newDigit = digit.:+(8)

        newDigit should be(Digit3[Int](10, 9, 8))
      }
    }

    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10, 9 )")
      }
    }
  }
}
