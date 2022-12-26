package fingertree.implFingerTree.implTreeComponents.views

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import view.implView.implViewLeftCons.ViewLeftCons
import single.implSingle.Single
import fingertree.implFingerTree.ITreeComponent

class SpecViewLeftCons extends AnyWordSpec with Matchers {
  "A ViewLeftCons" when {

    val viewLeftCons: ViewLeftCons[Int] = ViewLeftCons(10, Single(10))

    "accessing head" should {
      "return right head" in {
        val head: Int = viewLeftCons.head

        head should be (10)
      }
    }

    "accessing tail" should {
      "return right tail" in {
        val tail: ITreeComponent[Int] = viewLeftCons.tail 
        
        tail should be (Single(10))
      }
    }

    "checking if its empty" should {
      "return false" in {
        val isEmpty: Boolean = viewLeftCons.isEmpty 
        
        isEmpty should be (false)
      }
    }
  }
}
