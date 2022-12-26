package fingertree.implFingerTree.implTreeComponents.views

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import view.implView.implViewRightCons.ViewRightCons
import single.implSingle.Single
import fingertree.implFingerTree.ITreeComponent

class SpecViewRightCons extends AnyWordSpec with Matchers {
  "A ViewRightCons" when {

    val viewRightCons: ViewRightCons[Int] = ViewRightCons(10, Single(10))

    "accessing last" should {
      "return right last" in {
        val last: Int = viewRightCons.last 
        
        last should be (10)
      }
    }

    "accessing init" should {
      "return right init" in {
        val init: ITreeComponent[Int] = viewRightCons.init 
        
        init should be (Single(10))
      }
    }

    "checking if its empty" should {
      "return false" in {
        val isEmpty: Boolean = viewRightCons.isEmpty 
        
        isEmpty should be (false)
      }
    }
  }
}
