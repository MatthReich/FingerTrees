package single.implSingle

import deep.implDeep.Deep
import digit.IDigit
import digit.implDigit.Digit1
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import node.INode
import single.ISingle
import view.IView
import view.implView.IViewLeft
import view.implView.IViewRight
import view.implView.implViewLeftCons.ViewLeftCons
import view.implView.implViewRightCons.ViewRightCons

case class Single[A](entry: A) extends ISingle[A] with ITreeComponent[A]:
  override def :+[B >: A](newEntry: B): ITreeComponent[B] =
    Deep(Digit1[A](entry), Empty(), Digit1[B](newEntry))

  override def +:[B >: A](newEntry: B): ITreeComponent[B] =
    Deep(Digit1[B](newEntry), Empty(), Digit1[A](entry))

  override def ++[B >: A](treeToConcat: ITreeComponent[B]): ITreeComponent[B] =
    entry +: treeToConcat

  override def size: Int =
    entry match
      case component: ITreeComponent[A] @unchecked => component.size
      case digit: IDigit[A] @unchecked             => digit.size
      case node: INode[A] @unchecked               => node.size
      case _                                       => 1

  override def isEmpty: Boolean = false

  override def head: Option[A] = Some(entry)

  override def last: Option[A] = Some(entry)

  override def init: Option[ITreeComponent[A]] = Some(Empty())

  override def tail: Option[ITreeComponent[A]] = Some(Empty())

  override def toList: List[A] = entry :: Nil

  override def toString: String = s"Single( ${entry.toString()} )"

  override def viewRight: Option[IViewRight[A]] =
    Some(ViewRightCons(entry, Empty()))

  override def viewLeft: Option[IViewLeft[A]] =
    Some(ViewLeftCons(entry, Empty()))
