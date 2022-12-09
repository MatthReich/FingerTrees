package fingertrees.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit2
import digit.implDigit.Digit3

class SpecDigits2 extends AnyWordSpec with Matchers {

  "A Digit with 2 element" when {

    "appending a new element" should {
    
      val digit: Digit2[Int] = Digit2(10, 9)

      "return a new Digit with 3 elements" in {

        val newDigit = digit.:+(8)

        newDigit should be(Digit3[Int](10, 9, 8))
      }
    }

    "prepending a new element" should {

      val digit: Digit2[Int] = Digit2(9, 10)

      
      "return a new Digit with 3 elements" in {

        val newDigit = digit.+:(8)

        newDigit should be(Digit3[Int](8, 9, 10))
      }
    }

    "checking size" should {

      val digit: Digit2[Int] = Digit2(10, 9)


      "be 2 when only 2 Value are stored" in {
        digit.size should be(2)
      }
    }

    "calling toString" should {

      val digit: Digit2[Int] = Digit2(10, 9)

      "be presented right" in {
        digit.toString should be("Digit( 10, 9 )")
      }
    }
  }
}
