package fingertrees.digits

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import digit.implDigit.Digit4

class SpecDigits4 extends AnyWordSpec with Matchers {

  "A Digit with 4 element" when {

    val digit: Digit4[Int] = Digit4(10, 9, 8, 7)

    "appending a new element" should {
      "triggers a sys error" in {
        try {
          val res = digit.:+(5)
          fail()
        } catch {
          case ex: UnsupportedOperationException => 
            ex.getMessage() should be("Digit4 is already max sized. Can´t be appended!")
        }
      }
    }

    "prepending a new element" should {
      "triggers a sys error" in {
        try {
          val res = digit.+:(5)
          fail()
        } catch {
          case ex: UnsupportedOperationException => 
            ex.getMessage() should be("Digit4 is already max sized. Can´t be prepended!")
        }
      }
    }

    "checking size" should {
      "be 4 when only 4 Value are stored" in {
        digit.size should be(4)
      }
    }

    "calling toString" should {
      "be presented right" in {
        digit.toString should be("Digit( 10, 9, 8, 7 )")
      }
    }
  }
}
