package fingertrees.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit1
import digit.implDigit.Digit2

class SpecDigits1 extends AnyWordSpec with Matchers {

  "A Digit with 1 element" when {

    val digit: Digit1[Int] = Digit1(10)

    "adding a new element" should {
      "return a new Digit with 2 elements" in {

        val newDigit = digit.:+(10)

        newDigit should be(Digit2[Int](10, 10))
      }
    }

    "checking size" should {
      "be 1 when only 1 Value is stored" in {
        digit.size should be(1)
      }
    }

    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10 )")
      }
    }
  }
}
