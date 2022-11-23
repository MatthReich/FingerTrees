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

    val empty: IEmpty[Int] = Empty[Int]()

    "adding a new element" should {
      "return a Deep with 2 Digit 1 and Empty" in {
        val newEmpty = empty.+:(10)

        newEmpty should be(Single(10))
      }
    }
    "calling toString" should {
      "be presented right" in {
        empty.toString should be("Empty()")
      }
    }
  }
}