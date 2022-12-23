package fingertrees.empty

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.implSingle.Single
import deep.implDeep.Deep
import digit.implDigit.Digit1
import empty.implEmpty.Empty
import empty.IEmpty
import org.mockito.Mockito.mock;
import org.mockito.Mockito.when;
import single.ISingle

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

    "concating TreeComponents" should {
      "return the given Tree" in {
        val singleToConcat: ISingle[Int] = Single[Int](10)

        val concated = empty ++ singleToConcat

        concated should be(Single(10))
      }
    }

    "checking size" should {
      "give back 0" in {
        empty.size should be(0)
      }
    }

    "checking if its empty" should {
      "be false" in {
        empty.isEmpty should be(true)
      }
    }

    "accessing head" should {
      "return right head element" in {
        empty.head should be(None)
      }
    }

    "accessing last" should {
      "return right last element" in {
        empty.last should be(None)
      }
    }

    "getting init" should {
      "return None" in {
        empty.init should be(None)
      }
    }

    "getting tail" should {
      "return None" in {
        empty.tail should be(None)
      }
    }

    "calling toList" should {
      "return Nil" in {
        empty.toList should be (Nil)
      }
    }

    "calling toString" should {
      "be presented right" in {
        empty.toString should be("Empty()")
      }
    }

    "viewRight" should {
      "return None" in {
        empty.viewRight should be(None)
      }
    }

    "viewLeft" should {
      "return None" in {
        empty.viewLeft should be(None)
      }
    }
  }
}
