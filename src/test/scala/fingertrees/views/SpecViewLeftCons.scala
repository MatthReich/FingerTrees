package fingertrees.views

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import view.implView.implViewLeftCons.ViewLeftCons
import single.implSingle.Single

class SpecViewLeftCons extends AnyWordSpec with Matchers {
  "A ViewLeftCons" when {

    val viewLeftCons: ViewLeftCons[Int] = ViewLeftCons(10, Single(10))

    "accessing head" should {
      "return right head" in {
        viewLeftCons.head should be(10)
      }
    }

    "accessing tail" should {
      "return right tail" in {
        viewLeftCons.tail should be(Single(10))
      }
    }

    "checking if its empty" should {
      "return false" in {
        viewLeftCons.isEmpty should be(false)
      }
    }
  }
}
