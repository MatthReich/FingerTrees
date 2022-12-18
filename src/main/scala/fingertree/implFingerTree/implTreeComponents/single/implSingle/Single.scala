package single.implSingle

import single.ISingle
import empty.implEmpty.Empty
import deep.IDeep
import deep.implDeep.Deep
import digit.IDigit
import digit.implDigit.Digit1
import node.INode
import fingertree.implFingerTree.ITreeComponent
import view.IView
import view.implView.IViewLeft
import view.implView.IViewRight
import view.implView.implViewLeftCons.ViewLeftCons
import view.implView.implViewRightCons.ViewRightCons

final case class Single[A](entry: A) extends ISingle[A], ITreeComponent[A]:
  override def :+[B >: A](newEntry: B): ITreeComponent[B] =
    Deep(Digit1[A](entry), Empty(), Digit1[B](newEntry))

  override def +:[B >: A](newEntry: B): ITreeComponent[B] =
    Deep(Digit1[B](newEntry), Empty(), Digit1[A](entry))

  override def ++[B >: A](treeToConcat: ITreeComponent[B]): ITreeComponent[B] =
    entry +: treeToConcat

  override def size: Int =
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A]             => digit.size
      case node: INode[A]               => node.size
      case _                            => 1

  override def isEmpty: Boolean = false

  override def head: Option[A] =
    entry match
      case component: ITreeComponent[A] => component.head
      case digit: IDigit[A]             => digit.head
      case _                            => Some(entry)

  override def last: Option[A] =
    entry match
      case component: ITreeComponent[A] => component.last
      case digit: IDigit[A]             => digit.last
      case _                            => Some(entry)

  override def init: Option[ITreeComponent[A]] = Some(Empty())

  override def viewRight: IViewRight[A] = ViewRightCons(Some(entry), Empty())

  override def tail: Option[ITreeComponent[A]] = Some(Empty())

  override def viewLeft: IViewLeft[A] = ViewLeftCons(Some(entry), Empty())

  override def toString: String = s"Single( ${entry.toString()} )"
