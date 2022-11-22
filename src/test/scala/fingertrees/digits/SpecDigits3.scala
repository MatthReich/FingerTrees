package fingertrees.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit3
import digit.implDigit.Digit4

class SpecDigits3 extends AnyWordSpec with Matchers {

  "A Digit with 1 element" when {
    val digit: Digit3[Int] = Digit3(10, 9, 8)

    "adding a new element" should {
      "return a new Digit with 2 elements" in {
        val newDigit = digit.+:(7)

        newDigit should be(Digit4[Int](10, 9, 8, 7))
      }
    }
    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10, 9, 8 )")
      }
    }
  }
}
