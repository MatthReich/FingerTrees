package fingertrees.empty

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.implSingle.Single
import deep.implDeep.Deep
import digit.implDigit.Digit1
import empty.implEmpty.Empty
import empty.IEmpty

class SpecEmpty extends AnyWordSpec with Matchers {

  "An Empty" when {

    val empty: IEmpty = Empty()

    "appending a new element" should {
      "return a Single" in {
        val newEmpty = empty.:+(10)

        newEmpty should be(Single(10))
      }
    }

    "prepending a new element" should {
      "return a Single" in {
        val newEmpty = empty.+:(10)

        newEmpty should be(Single(10))
      }
    }

    "checking size" should {
      "give back 0" in {
        empty.size should be(0)
      }
    }

    "calling toString" should {
      "be presented right" in {
        empty.toString should be("Empty()")
      }
    }
  }
}