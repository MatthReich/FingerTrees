package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode
import fingertree.implFingerTree.ITreeComponent

final case class Digit2[A](entry1: A, entry2: A) extends IDigit[A]:
  override def :+[B >: A](newEntry: B): IDigit[B] =
    Digit3(entry1, entry2, newEntry)

  override def +:[B >: A](newEntry: B): IDigit[B] =
    Digit3(newEntry, entry1, entry2)

  override def size: Int = measureSize(entry1) + measureSize(entry2)

  override def isEmpty: Boolean = false

  override def head: Option[A] =
    entry1 match
      case component: ITreeComponent[A] => component.head
      case digit: IDigit[A]             => digit.head
      case _                            => Some(entry1)

  override def last: Option[A] =
    entry2 match
      case component: ITreeComponent[A] => component.last
      case digit: IDigit[A]             => digit.last
      case _                            => Some(entry2)

  override def toList: List[A] = entry1 :: entry2 :: Nil

  override def toString: String =
    s"Digit( ${entry1.toString}, ${entry2.toString} )"

  private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A]             => digit.size
      case _                            => 1
