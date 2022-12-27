package fingertree.implFingerTree.implTreeComponents.deep

import deep.IDeep
import deep.implDeep.Deep
import digit.implDigit.Digit1
import digit.implDigit.Digit2
import digit.implDigit.Digit3
import digit.implDigit.Digit4
import empty.IEmpty
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import node.INode
import node.implNode.Node3
import node.nodeImpl.Node2
import org.mockito.Mockito.mock
import org.mockito.Mockito.when
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import single.ISingle
import single.implSingle.Single
import view.implView.implViewLeftCons.ViewLeftCons
import view.implView.implViewRightCons.ViewRightCons
import view.implView.IViewRight
import view.implView.IViewLeft

class SpecDeep extends AnyWordSpec with Matchers {

  "A Deep" when {

    val deep: IDeep[Int] = Deep(Digit1(10), Empty(), Digit1(9))
    val mockedDigit = mock(classOf[Digit1[Int]])
    val mockedEmpty = mock(classOf[Empty])
    val mockedSingle = mock(classOf[Single[Node3[Int]]])

    "appending a new element" should {
      "return a Deep with 2 Digits on right when 1 Digit before" in {
        val newDeep = deep :+ 8

        val expectedDeep: IDeep[Int] =
          Deep[Int](Digit1(10), Empty(), Digit2(9, 8))
        newDeep should be(expectedDeep)
      }

      "return a Deep with 3 Digits on right when 2 Digits before" in {
        val newDeep = deep :+ 8 :+ 7

        val expectedDeep: IDeep[Int] =
          Deep[Int](Digit1(10), Empty(), Digit3(9, 8, 7))
        newDeep should be(expectedDeep)
      }

      "return a Deep with 4 Digits on right when 3 Digits before" in {
        val newDeep = deep :+ 8 :+ 7 :+ 6

        val expectedDeep: IDeep[Int] =
          Deep[Int](Digit1(10), Empty(), Digit4(9, 8, 7, 6))
        newDeep should be(expectedDeep)
      }

      "return a Deep with Digit1 as prefix, Digit2 as suffix and as tree a Single with Node3" in {
        val newDeep = deep :+ 8 :+ 7 :+ 6 :+ 5

        val expectedDeep: IDeep[Int] =
          Deep[Int](Digit1(10), Single(Node3(9, 8, 7)), Digit2(6, 5))
        newDeep should be(expectedDeep)
      }
    }

    "prepending a new element" should {

      val deep: Deep[Int] = Deep(Digit1(9), Empty(), Digit1(10))

      "return a Deep( Digit2 Empty Digit1 ) structure after prepending 1 element" in {
        val newDeep = 8 +: deep

        val expectedDeep: IDeep[Int] =
          Deep[Int](Digit2(8, 9), Empty(), Digit1(10))
        newDeep should be(expectedDeep)
      }

      "return a Deep( Digit2 Single(Node3) Digit1 ) structure after prepending to Digit4" in {
        val newDeep = 5 +: 6 +: 7 +: 8 +: deep

        val expectedDeep: IDeep[Int] =
          Deep[Int](Digit2(5, 6), Single(Node3(7, 8, 9)), Digit1(10))
        newDeep should be(expectedDeep)
      }
    }

    "concatenate TreeComponents" should {
      "return a itself when concatenate an Empty" in {
        val emptyToConcat: IEmpty = Empty()

        val concatenated = deep ++ emptyToConcat

        concatenated should be(deep)
      }

      "return a Deep( Digit1 Empty Digit2 ) when concatenate a Single where the right Digit contains the new entry on last position" in {
        val singleToConcat: ISingle[Int] = Single[Int](8)

        val concatenated = deep ++ singleToConcat

        val expectedDeep: IDeep[Int] = Deep(Digit1(10), Empty(), Digit2(9, 8))
        concatenated should be(expectedDeep)
      }

      "return a Deep( Digit1 Single( Node2 ) Digit1 ) when having a Deep with Empty" in {
        val deepToConcat: IDeep[Int] = Deep(Digit1(8), Empty(), Digit1(7))

        val concatenated = deep ++ deepToConcat

        val expectedDeep: IDeep[Int] =
          Deep(Digit1(10), Single(Node2(9, 8)), Digit1(7))
        concatenated should be(expectedDeep)
      }

      "return a Deep( Digit1, Deep( Digit1( Node2 ), Empty, Digit1( Node2 )), Digit1) when concatenate a Deep with Empty" in {
        val deep: IDeep[Int] = Deep(Digit1(10), Single(Node2(9, 8)), Digit1(7))
        val deepToConcat: IDeep[Int] = Deep(Digit1(6), Empty(), Digit1(5))

        val concatenated = deep ++ deepToConcat

        val midDeep: IDeep[INode[Int]] =
          Deep(Digit1(Node2(9, 8)), Empty(), Digit1(Node2(7, 6)))
        val expectedDeep: IDeep[Int] = Deep(Digit1(10), midDeep, Digit1(5))
        concatenated should be(expectedDeep)
      }

      "return a Deep( Digit1, Deep( Digit1( Node2 ), Empty, Digit1( Node2 )), Digit1) when having a Deep with Single" in {
        val deep: IDeep[Int] = Deep(Digit1(10), Single(Node2(9, 8)), Digit1(7))
        val deepToConcat: IDeep[Int] =
          Deep(Digit1(6), Single(Node2(5, 4)), Digit1(3))

        val concatenated = deep ++ deepToConcat

        val midDeep: IDeep[INode[Int]] =
          Deep(Digit2(Node2(9, 8), Node2(7, 6)), Empty(), Digit1(Node2(5, 4)))
        val expectedDeep: IDeep[Int] = Deep(Digit1(10), midDeep, Digit1(3))
        concatenated should be(expectedDeep)
      }

      "return a Deep( Digit1, Deep( Digit1( Node2 )), Empty, Digit3( Node2, Node2, Node2 )), Digit1) when concatenate a Deep with Single" in {
        val deep: IDeep[Int] = Deep(
          Digit1(10),
          Deep(Digit1(Node2(9, 8)), Empty(), Digit1(Node2(7, 6))),
          Digit1(5)
        )
        val deepToConcat: IDeep[Int] =
          Deep(Digit1(4), Single(Node2(3, 2)), Digit1(1))

        val concatenated = deep ++ deepToConcat

        val midDeep: IDeep[INode[Int]] = Deep(
          Digit1(Node2(9, 8)),
          Empty(),
          Digit3(Node2(7, 6), Node2(5, 4), Node2(3, 2))
        )
        val expectedDeep: IDeep[Int] = Deep(Digit1(10), midDeep, Digit1(1))
        concatenated should be(expectedDeep)
      }

      "return a Deep( Digit1, Deep( Digit1( Node2 ), Single( Node3( Node2, Node2, Node2)), Digit1( Node2)), Digit1) when having two Deeps" in {
        val deep: IDeep[Int] = Deep(
          Digit1(10),
          Deep(Digit1(Node2(9, 8)), Empty(), Digit1(Node2(7, 6))),
          Digit1(5)
        )

        val concatenated = deep ++ deep

        val midDeep: IDeep[INode[Int]] = Deep(
          Digit1(Node2(9, 8)),
          Single(Node3(Node2(7, 6), Node2(5, 10), Node2(9, 8))),
          Digit1(Node2(7, 6))
        )
        val expectedDeep: IDeep[Int] = Deep(Digit1(10), midDeep, Digit1(5))
        concatenated should be(expectedDeep)
      }

      "return a Deep( Digit1, Single( Node2 ), Digit1 ) when concatenate 2 suffix and prefix" in {
        val deepToConcat: IDeep[Int] = Deep(Digit1(8), Empty(), Digit1(7))

        val concatenated = deep ++ deepToConcat

        val expectedDeep: IDeep[Int] =
          Deep(Digit1(10), Single(Node2(9, 8)), Digit1(7))
        concatenated should be(expectedDeep)
      }

      "return a Deep( Digit1, Single( Node3 ), Digit1 ) when concatenate 3 suffix and prefix" in {
        val deepToConcat: IDeep[Int] = Deep(Digit2(8, 7), Empty(), Digit1(6))

        val concatenated = deep ++ deepToConcat

        val expectedDeep: IDeep[Int] =
          Deep(Digit1(10), Single(Node3(9, 8, 7)), Digit1(6))
        concatenated should be(expectedDeep)
      }

      "return a Deep( Digit1, Deep( Digit1( Node2 ), Empty, Digit1( Node2 )) when concatenate 4 suffix and prefix" in {
        val deepToConcat: IDeep[Int] = Deep(Digit3(8, 7, 6), Empty(), Digit1(5))

        val concatenated = deep ++ deepToConcat

        val midDeep: IDeep[INode[Int]] =
          Deep(Digit1(Node2(9, 8)), Empty(), Digit1(Node2(7, 6)))
        val expectedDeep: IDeep[Int] = Deep(Digit1(10), midDeep, Digit1(5))
        concatenated should be(expectedDeep)
      }

      "return a Deep( Digit1, Deep( Digit1( Node3 ), Empty, Digit1( Node2 )), Digit1 ) when concatenate more then 4 suffix and prefix" in {
        val deepToConcat: IDeep[Int] =
          Deep(Digit4(8, 7, 6, 5), Empty(), Digit1(4))

        val concatenated = deep ++ deepToConcat

        val midDeep: IDeep[INode[Int]] =
          Deep(Digit1(Node3(9, 8, 7)), Empty(), Digit1(Node2(6, 5)))
        val expectedDeep: IDeep[Int] = Deep(Digit1(10), midDeep, Digit1(4))
        concatenated should be(expectedDeep)
      }
    }

    "checking size" should {
      "be 2 when only 2 Digits and 1 Empty" in {
        when(mockedDigit.size) thenReturn 1
        when(mockedEmpty.size) thenReturn 0
        val deep: IDeep[Int] = Deep(mockedDigit, mockedEmpty, mockedDigit)

        val size: Int = deep.size

        size should be(2)
      }

      "be 5 when Deep( Digit1 Single( Node3 ) Digit1 )" in {
        when(mockedDigit.size) thenReturn 1
        when(mockedSingle.size) thenReturn 3
        val deep: IDeep[Int] = Deep(mockedDigit, mockedSingle, mockedDigit)

        val size: Int = deep.size

        size should be(5)
      }
    }

    "checking if its empty" should {
      "be false" in {
        val isEmpty: Boolean = deep.isEmpty

        isEmpty should be(false)
      }
    }

    "accessing head" should {
      "return right head element" in {
        when(mockedDigit.head) thenReturn Some(10)
        val deep: IDeep[Int] = Deep(mockedDigit, Empty(), Digit1(9))

        val head: Option[Int] = deep.head

        head match
          case None => fail("Head was None instead of Some")
          case Some(head) =>
            head should be(10)
      }
    }

    "accessing last" should {
      "return right last element" in {
        when(mockedDigit.last) thenReturn Some(10)
        val deep: IDeep[Int] = Deep(Digit1(9), Empty(), mockedDigit)

        val last: Option[Int] = deep.last

        last match
          case None => fail("Last was None instead of Some")
          case Some(last) =>
            last should be(10)
      }
    }

    "accessing init" should {
      "return None when no init exists" in {
        when(mockedDigit.last) thenReturn None
        val deep = Deep(Digit1(10), Empty(), mockedDigit)

        val init: Option[ITreeComponent[Int]] = deep.init

        init should be(None)
      }

      "return Some( Deep ) when init exists" in {
        when(mockedDigit.last) thenReturn Some(Digit1(10))
        when(mockedDigit.init) thenReturn Some(Digit1(10))
        val deep = Deep(Digit1(10), Empty(), mockedDigit)

        val init: Option[_] = deep.init

        init match
          case None => fail("Init was None instead of Some")
          case Some(init) =>
            init should be(Deep(Digit1(10), Empty(), Digit1(10)))
      }
    }

    "accessing tail" should {
      "return None when no init exists" in {
        when(mockedDigit.head) thenReturn None
        val deep = Deep(mockedDigit, Empty(), Digit1(10))

        val tail: Option[ITreeComponent[Int]] = deep.tail

        deep.tail should be(None)
      }

      "return Some( Deep ) when init exists" in {
        when(mockedDigit.head) thenReturn Some(Digit1(10))
        when(mockedDigit.tail) thenReturn Some(Digit1(10))
        val deep = Deep(mockedDigit, Empty(), Digit1(10))

        val tail: Option[_] = deep.tail

        tail match
          case None => fail("Tail was None instead of Some")
          case Some(tail) =>
            tail should be(Deep(Digit1(10), Empty(), Digit1(10)))
      }
    }

    "calling toList" should {
      "return List of stored values deep down" in {
        when(mockedDigit.toList) thenReturn List(10)
        when(mockedSingle.toList) thenReturn List(Node2(9, 8))
        val deep = Deep(mockedDigit, mockedSingle, mockedDigit)

        val list: List[Int] = deep.toList

        val expectedList: List[Int] = List(10, 9, 8, 10)
        list should be(expectedList)
      }
    }

    "calling toString" should {
      "be presented right" in {
        val stringRepresentation: String = deep.toString

        val expectedString: String = "Deep( Digit( 10 ), Empty(), Digit( 9 ) )"
        stringRepresentation should be(expectedString)
      }
    }

    "viewRight" should {
      "return None when no head of prefix exists" in {
        when(mockedDigit.last) thenReturn None
        val deep: IDeep[Int] = Deep(mockedDigit, Empty(), mockedDigit)

        val viewRight: Option[IViewRight[Int]] = deep.viewRight

        viewRight should be(None)
      }

      "return last of prefix and init of suffix when init is None" in {
        when(mockedDigit.last) thenReturn Some(10)
        when(mockedDigit.init) thenReturn None
        when(mockedDigit.toTreeComponent) thenReturn Single(9)
        val deep = Deep(mockedDigit, Empty(), mockedDigit)

        val viewRight: Option[IViewRight[Int]] = deep.viewRight

        viewRight match
          case None => fail("ViewRight was None instead of Some")
          case Some(viewRight) =>
            viewRight should be(ViewRightCons(10, Single(9)))
      }

      "return last of prefix and init of suffix when init is Some" in {
        when(mockedDigit.last) thenReturn Some(10)
        when(mockedDigit.init) thenReturn Some(Digit1(10))
        when(mockedDigit.toTreeComponent) thenReturn Single(9)
        val deep = Deep(mockedDigit, Empty(), mockedDigit)

        val viewRight: Option[IViewRight[Int]] = deep.viewRight

        viewRight match
          case None => fail("ViewRight was None instead of Some")
          case Some(viewRight) =>
            viewRight should be(
              ViewRightCons(10, Deep(mockedDigit, Empty(), Digit1(10)))
            )
      }

      "return new Deep as init" in {
        when(mockedDigit.last) thenReturn Some(10)
        when(mockedDigit.init) thenReturn None
        when(mockedDigit.toTreeComponent) thenReturn Single(9)
        val deep = Deep(mockedDigit, Single(Node2(9, 8)), mockedDigit)

        val viewRight: Option[IViewRight[Int]] = deep.viewRight

        viewRight match
          case None => fail("ViewRight was None instead of Some")
          case Some(viewRight) =>
            viewRight should be(
              ViewRightCons(10, Deep(mockedDigit, Empty(), Digit1(Node2(9, 8))))
            )
      }
    }

    "viewLeft" should {
      "return None when no head of prefix exists" in {
        when(mockedDigit.head) thenReturn None
        val deep: IDeep[Int] = Deep(mockedDigit, Empty(), mockedDigit)

        val viewLeft: Option[IViewLeft[Int]] = deep.viewLeft

        viewLeft should be(None)
      }

      "return head of prefix and tail of suffix when tail is None" in {
        when(mockedDigit.head) thenReturn Some(10)
        when(mockedDigit.tail) thenReturn None
        when(mockedDigit.toTreeComponent) thenReturn Single(9)
        val deep = Deep(mockedDigit, Empty(), mockedDigit)

        val viewLeft: Option[IViewLeft[Int]] = deep.viewLeft

        viewLeft match
          case None => fail("ViewLeft was None instead of Some")
          case Some(viewLeft) =>
            viewLeft should be(ViewLeftCons(10, Single(9)))
      }

      "return head of prefix and tail of suffix when tail is Some" in {
        when(mockedDigit.head) thenReturn Some(10)
        when(mockedDigit.tail) thenReturn Some(Digit1(10))
        when(mockedDigit.toTreeComponent) thenReturn Single(9)
        val deep = Deep(mockedDigit, Empty(), mockedDigit)

        val viewLeft: Option[IViewLeft[Int]] = deep.viewLeft

        viewLeft match
          case None => fail("ViewLeft was None instead of Some")
          case Some(viewLeft) =>
            viewLeft should be(
              ViewLeftCons(10, Deep(Digit1(10), Empty(), mockedDigit))
            )
      }

      "return new Deep as tail" in {
        when(mockedDigit.head) thenReturn Some(10)
        when(mockedDigit.tail) thenReturn None
        when(mockedDigit.toTreeComponent) thenReturn Single(9)
        val deep = Deep(mockedDigit, Single(Node2(9, 8)), mockedDigit)

        val viewLeft: Option[IViewLeft[Int]] = deep.viewLeft

        viewLeft match
          case None => fail("ViewLeft was None instead of Some")
          case Some(viewLeft) =>
            viewLeft should be(
              ViewLeftCons(10, Deep(Digit1(Node2(9, 8)), Empty(), mockedDigit))
            )
      }
    }
  }
}
