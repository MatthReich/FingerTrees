package fingertrees.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit4

class SpecDigits4 extends AnyWordSpec with Matchers {

  "A Digit with 4 element" when {

    val digit: Digit4[Int] = Digit4(10, 9, 8, 7)

    "adding a new element" should {
      "triggers a sys error" in {
        val res = digit.+:(5)

        res should be(None)
      }
    }
    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10, 9, 8, 7 )")
      }
    }
  }
}
