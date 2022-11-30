package fingertrees.single

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.implSingle.Single
import deep.implDeep.Deep
import digit.implDigit.Digit1
import empty.implEmpty.Empty

class SpecSingle extends AnyWordSpec with Matchers {

  "A Single" when {

    val single: Single[Int] = Single(10)

    "adding a new element" should {
      "return a Deep with 2 Digit 1 and Empty when only a value inside" in {
        val newSingle = single.+:(9)

        newSingle should be(Deep[Int](Digit1(10), Empty(), Digit1(9)))
      }
    }
    "calling toString" should {
      "be presented right" in {
        single.toString should be("Single( 10 )")
      }
    }
  }
}
