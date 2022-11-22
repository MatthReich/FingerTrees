package fingertrees.single

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.implSingle.Single


class SpecSingle extends AnyWordSpec with Matchers {

  "A Single" when {

    val single: Single[Int] = Single(10)

    "calling toString" should {
      "be presented right" in {
        single.toString should be("Single( 10 )")
      }
    }
  }
}