package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode
import fingertree.implFingerTree.ITreeComponent
import empty.implEmpty.Empty

final case class Digit3[A](entry1: A, entry2: A, entry3: A) extends IDigit[A]:
  override def :+[B >: A](newEntry: B): IDigit[B] =
    Digit4(entry1, entry2, entry3, newEntry)

  override def +:[B >: A](newEntry: B): IDigit[B] =
    Digit4(newEntry, entry1, entry2, entry3)

  override def size: Int =
    measureSize(entry1) + measureSize(entry2) + measureSize(entry3)

  override def isEmpty: Boolean = false

  override def head: Option[A] =
    entry1 match
      case component: ITreeComponent[A] => component.head
      case digit: IDigit[A]             => digit.head
      case _                            => Some(entry1)

  override def last: Option[A] =
    entry3 match
      case component: ITreeComponent[A] => component.last
      case digit: IDigit[A]             => digit.last
      case _                            => Some(entry3)

  override def tail: Option[IDigit[A]] = Some(Digit2(entry2, entry3))

  override def toList: List[A] = entry1 :: entry2 :: entry3 :: Nil

  override def toTreeComponent: ITreeComponent[A] =
    Deep(Digit2(entry1, entry2), Empty(), Digit1(entry3))

  override def toString: String =
    s"Digit( ${entry1.toString}, ${entry2.toString}, ${entry3.toString} )"

  private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A]             => digit.size
      case node: INode[A]               => node.size
      case _                            => 1
