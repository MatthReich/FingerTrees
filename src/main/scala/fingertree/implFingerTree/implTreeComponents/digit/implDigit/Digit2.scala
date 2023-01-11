package digit.implDigit

import deep.implDeep.Deep
import digit.IDigit
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import node.INode

case class Digit2[A](entry1: A, entry2: A) extends IDigit[A]:
  override def :+[B >: A](newEntry: B): IDigit[B] =
    Digit3(entry1, entry2, newEntry)

  override def +:[B >: A](newEntry: B): IDigit[B] =
    Digit3(newEntry, entry1, entry2)

  override def size: Int = measureSize(entry1) + measureSize(entry2)

  override def isEmpty: Boolean = false

  override def head: Option[A] = Some(entry1)

  override def last: Option[A] = Some(entry2)

  override def init: Option[IDigit[A]] = Some(Digit1(entry1))

  override def tail: Option[IDigit[A]] = Some(Digit1(entry2))

  override def toList: List[A] = entry1 :: entry2 :: Nil

  override def toTreeComponent: ITreeComponent[A] =
    Deep(Digit1(entry1), Empty(), Digit1(entry2))

  override def toString: String =
    s"Digit( ${entry1.toString}, ${entry2.toString} )"

  private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] @unchecked => component.size
      case digit: IDigit[A] @unchecked             => digit.size
      case node: INode[A] @unchecked               => node.size
      case _                                       => 1
