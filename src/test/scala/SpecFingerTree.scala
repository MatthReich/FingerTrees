package fingertrees

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import fingertree.implFingerTree.FingerTree
import fingertree.implFingerTree.ITreeComponent
import org.scalacheck.Gen
import org.scalacheck.Arbitrary.arbitrary
import fingertree.IFingerTree
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class SpecFingerTree
    extends AnyWordSpec
    with Matchers
    with ScalaCheckDrivenPropertyChecks {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfiguration(minSuccessful = 50)

  "A FingerTree" when {

    val fingerTree: FingerTree[Int] = FingerTree()
    val ints: Gen[Int] = arbitrary[Int]

    "checking size" should {
      "increase by one after every append" in {
        var fingerTree: IFingerTree[Int] = FingerTree()
        var count = 0;
        fingerTree.size should be(count)

        forAll(ints) { (toAppend: Int) =>
          count += 1
          fingerTree = fingerTree.append(toAppend)
          fingerTree.size should be(count)
        }
      }

      "increase by one after every prepend" in {
        var fingerTree: IFingerTree[Int] = FingerTree()
        var count = 0;
        fingerTree.size should be(count)

        forAll(ints) { (toAppend: Int) =>
          count += 1
          fingerTree = fingerTree.prepend(toAppend)
          fingerTree.size should be(count)
        }
      }

      "gets correctly after mixing append and prepend" in {
        var fingerTree: IFingerTree[Int] = FingerTree()
        var count = 0;
        fingerTree.size should be(count)

        forAll(ints) { (toAppend: Int) =>
          count += 1
          fingerTree =
            if (Gen.oneOf("prepend", "append").sample.get == "prepend") then
              println("prepend")
              fingerTree.prepend(toAppend)
            else
              println("append")
              fingerTree.append(toAppend)
          fingerTree.size should be(count)
        }
      }

      "gets correctly after every concat" in {
        val fingerTreeToConcat: IFingerTree[Int] =
          FingerTree().append(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        var fingerTree: IFingerTree[Int] = FingerTree()
        var count = 0;

        forAll { () =>
          count += 10;
          fingerTree = fingerTree.concat(fingerTreeToConcat)
          fingerTree.size should be(count)
        }
      }
    }

    "calling toString" should {
      "be presented right when only created" in {
        fingerTree.toString should be("Empty()")
      }
    }
  }

}
