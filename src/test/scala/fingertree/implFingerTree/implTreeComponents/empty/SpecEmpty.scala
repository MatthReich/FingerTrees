package fingertree.implFingerTree.implTreeComponents.empty

import deep.implDeep.Deep
import digit.implDigit.Digit1
import empty.IEmpty
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.ISingle
import single.implSingle.Single
import view.implView.IViewLeft
import view.implView.IViewRight

class SpecEmpty extends AnyWordSpec with Matchers {

  "An Empty" when {

    val empty: IEmpty = Empty()

    "appending a new element" should {
      "return a Single" in {
        val appended: ITreeComponent[Int] = empty :+ 10

        val expectedTreeComponent: ITreeComponent[Int] = Single(10)
        appended should be(expectedTreeComponent)
      }
    }

    "prepending a new element" should {
      "return a Single" in {
        val prepended: ITreeComponent[Int] = 10 +: empty

        val expectedTreeComponent: ITreeComponent[Int] = Single(10)
        prepended should be(expectedTreeComponent)
      }
    }

    "concatenate TreeComponents" should {
      "return the given Tree" in {
        val singleToConcat: ISingle[Int] = Single[Int](10)

        val concatenated = empty ++ singleToConcat

        val expectedTreeComponent: ITreeComponent[Int] = Single(10)
        concatenated should be(expectedTreeComponent)
      }
    }

    "checking size" should {
      "give back 0" in {
        val size: Int = empty.size

        size should be(0)
      }
    }

    "checking if its empty" should {
      "be false" in {
        val isEmpty: Boolean = empty.isEmpty

        isEmpty should be(true)
      }
    }

    "accessing head" should {
      "return right head element" in {
        val head: Option[Int] = empty.head

        head should be(None)
      }
    }

    "accessing last" should {
      "return right last element" in {
        val last: Option[Int] = empty.last

        last should be(None)
      }
    }

    "getting init" should {
      "return None" in {
        val init: Option[ITreeComponent[Int]] = empty.init

        init should be(None)
      }
    }

    "getting tail" should {
      "return None" in {
        val tail: Option[ITreeComponent[Int]] = empty.tail

        tail should be(None)
      }
    }

    "calling toList" should {
      "return Nil" in {
        val list: List[Int] = empty.toList

        val expectedList: List[Int] = Nil
        list should be(expectedList)
      }
    }

    "calling toString" should {
      "be presented right" in {
        val stringRepresentation: String = empty.toString

        val expectedString: String = "Empty()"
        stringRepresentation should be(expectedString)
      }
    }

    "viewRight" should {
      "return None" in {
        val viewRight: Option[IViewRight[Int]] = empty.viewRight

        viewRight should be(None)
      }
    }

    "viewLeft" should {
      "return None" in {
        val viewLeft: Option[IViewLeft[Int]] = empty.viewLeft

        viewLeft should be(None)
      }
    }
  }
}
