package fingertree.implFingerTree

import digit.implDigit.Digit1
import empty.IEmpty
import empty.implEmpty.Empty
import fingertree.IFingerTree
import fingertree.implFingerTree.FingerTree
import fingertree.implFingerTree.ITreeComponent
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import single.ISingle
import single.implSingle.Single

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
        var count: Int = 0

        fingerTree.size should be(count)
        forAll(ints) { (toAppend: Int) =>
          count += 1
          fingerTree = fingerTree.append(toAppend)

          fingerTree.size should be(count)
        }
      }

      "increase by one after every prepend" in {
        var fingerTree: IFingerTree[Int] = FingerTree()
        var count = 0

        fingerTree.size should be(count)
        forAll(ints) { (toAppend: Int) =>
          count += 1
          fingerTree = fingerTree.prepend(toAppend)

          fingerTree.size should be(count)
        }
      }

      "gets correctly after mixing append and prepend" in {
        val gen: Gen[String] = Gen.oneOf("prepend", "append")
        var fingerTree: IFingerTree[Int] = FingerTree()
        var count = 0;

        fingerTree.size should be(count)
        forAll(ints) { (toAppend: Int) =>
          count += 1
          fingerTree =
            if (gen.sample.get == "prepend") then fingerTree.prepend(toAppend)
            else fingerTree.append(toAppend)

          fingerTree.size should be(count)
        }
      }

      "gets correctly after every concat" in {
        val fingerTreeToConcat: IFingerTree[Int] =
          FingerTree().append(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        var fingerTree: IFingerTree[Int] = FingerTree()
        var count = 0;

        forAll { () =>
          count += 10
          fingerTree = fingerTree.concat(fingerTreeToConcat)

          fingerTree.size should be(count)
        }
      }
    }

    "checking if isEmpty" should {
      "be true when only created" in {
        val isEmpty: Boolean = fingerTree.isEmpty

        isEmpty should be(true)
      }

      "be false when element inside" in {
        val fingerTree: IFingerTree[Int] = FingerTree[Int]().append(10)

        val isEmpty: Boolean = fingerTree.isEmpty

        isEmpty should be(false)
      }
    }

    "getting head" should {
      "return None when empty" in {
        val head: Option[Int] = fingerTree.head

        head should be(None)
      }

      "return Some when something is stored in Single" in {
        val fingerTree: IFingerTree[Int] = FingerTree[Int]().append(10)

        val head: Option[Int] = fingerTree.head

        head should be(Some(10))
      }

      "return right Some while mixing append and prepend" in {
        val gen: Gen[String] = Gen.oneOf("prepend", "append")
        var fingerTree: IFingerTree[Int] = FingerTree()
        var head: Option[Int] = None

        fingerTree.head should be(head)
        forAll(ints) { (toAppend: Int) =>
          fingerTree =
            if (gen.sample.get == "prepend") then 
              head = Some(toAppend)
              fingerTree.prepend(toAppend)
            else 
              if head.isEmpty then head = Some(toAppend)
              fingerTree.append(toAppend)

          fingerTree.head should be(head)
        }
      }
    }

    "getting last" should {
      "return None when empty" in {
        val last: Option[Int] = fingerTree.last

        last should be(None)
      }

      "return Some when something is stored" in {
        val fingerTree: IFingerTree[Int] = FingerTree[Int]().append(10)

        val last: Option[Int] = fingerTree.last

        last should be(Some(10))
      }

      "return right Some while mixing append and prepend" in {
        val gen: Gen[String] = Gen.oneOf("prepend", "append")
        var fingerTree: IFingerTree[Int] = FingerTree()
        var last: Option[Int] = None

        fingerTree.head should be(last)
        forAll(ints) { (toAppend: Int) =>
          fingerTree =
            if (gen.sample.get == "prepend") then 
              if last.isEmpty then last = Some(toAppend)
              fingerTree.prepend(toAppend)
            else 
              last = Some(toAppend)
              fingerTree.append(toAppend)

          fingerTree.last should be(last)
        }
      }
    }

    "getting init" should {
      "be None when nothing is stored" in {
        val init: Option[IFingerTree[Int]] = fingerTree.init

        init should be(None)
      }

      "be Some( Empty ) when only Single is stored" in {
        val fingerTree: IFingerTree[Int] = FingerTree[Int]().append(10)

        val init: Option[IFingerTree[Int]] = fingerTree.init

        init match
          case None => fail("Init was None instead a Tree of Some(Empty)")
          case Some(newTree) =>
            val expectedTree = FingerTree[Int](treeHead = Empty())
            newTree should be(expectedTree)
      }

      "be Some( Single ) when Deep with 2 elements is stored" in {
        val fingerTree: IFingerTree[Int] = FingerTree[Int]().append(10, 9)

        val init: Option[IFingerTree[Int]] = fingerTree.init

        init match
          case None => fail("Init was None instead a Tree of Some(Empty)")
          case Some(newTree) =>
            val expectedTree: IFingerTree[Int] =
              FingerTree[Int](treeHead = Single[Int](10))
            newTree should be(expectedTree)
      }
    }

    "getting tail" should {
      "be None when nothing is stored" in {
        val tail: Option[IFingerTree[Int]] = fingerTree.tail

        tail should be(None)
      }

      "be Some( Empty ) when only Single is stored" in {
        val fingerTree: IFingerTree[Int] = FingerTree[Int]().append(10)

        val tail: Option[IFingerTree[Int]] = fingerTree.tail

        tail match
          case None => fail("Init was None instead a Tree of Some(Empty)")
          case Some(newTree) =>
            val expectedTree = FingerTree[Int](treeHead = Empty())
            newTree should be(expectedTree)
      }

      "be Some( Single ) when Deep with 2 elements is stored" in {
        val fingerTree: IFingerTree[Int] = FingerTree[Int]().append(10, 9)

        val tail: Option[IFingerTree[Int]] = fingerTree.tail

        tail match
          case None => fail("Init was None instead a Tree of Some(Empty)")
          case Some(newTree) =>
            val expectedTree = FingerTree[Int](treeHead = Single[Int](9))
            newTree should be(expectedTree)
      }
    }

    "calling toList" should {
      "return all elements in right order when appending" in {
        var fingerTree: IFingerTree[Int] = FingerTree()
        var list: List[Int] = Nil;

        fingerTree.toList should be(list)
        forAll(ints) { (toAppend: Int) =>
          list = list :+ toAppend
          fingerTree = fingerTree.append(toAppend)

          fingerTree.toList should be(list)
        }
      }

      "return all elements in right order when prepending" in {
        var fingerTree: IFingerTree[Int] = FingerTree()
        var list: List[Int] = Nil;

        fingerTree.toList should be(list)
        forAll(ints) { (toAppend: Int) =>
          list = toAppend +: list
          fingerTree = fingerTree.prepend(toAppend)

          fingerTree.toList should be(list)
        }
      }

      "gets correctly after mixing append and prepend" in {
        val gen: Gen[String] = Gen.oneOf("prepend", "append")
        var fingerTree: IFingerTree[Int] = FingerTree()
        var list: List[Int] = Nil;

        fingerTree.toList should be(list)
        forAll(ints) { (toAppend: Int) =>
          fingerTree = if (gen.sample.get == "prepend") then
            list = toAppend +: list
            fingerTree.prepend(toAppend)
          else
            list = list :+ toAppend
            fingerTree.append(toAppend)

          fingerTree.toList should be(list)
        }
      }

      "gets correctly after every concat" in {
        val fingerTreeToConcat: IFingerTree[Int] =
          FingerTree().append(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        var fingerTree: IFingerTree[Int] = FingerTree()
        var list: List[Int] = Nil;

        forAll { () =>
          list = list ::: List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
          fingerTree = fingerTree.concat(fingerTreeToConcat)

          fingerTree.toList should be(list)
        }
      }
    }

    "calling toString" should {
      "be presented right when only created" in {
        val stringRepresentation: String = fingerTree.toString

        val expectedString: String = "Empty()"
        stringRepresentation should be(expectedString)
      }
    }
  }
}
