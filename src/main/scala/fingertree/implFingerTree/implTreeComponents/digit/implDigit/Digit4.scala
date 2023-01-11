package digit.implDigit

import deep.implDeep.Deep
import digit.IDigit
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import node.INode
import single.implSingle.Single

case class Digit4[A](entry1: A, entry2: A, entry3: A, entry4: A)
    extends IDigit[A]:
  override def :+[B >: A](newEntry: B): IDigit[B] =
    throw new UnsupportedOperationException(
      "Digit4 is already max sized. Can´t be appended!"
    )

  override def +:[B >: A](newEntry: B): IDigit[B] =
    throw new UnsupportedOperationException(
      "Digit4 is already max sized. Can´t be prepended!"
    )

  override def size: Int =
    measureSize(entry1) + measureSize(entry2) + measureSize(entry3) + measureSize(entry4)

  override def isEmpty: Boolean = false

  override def head: Option[A] = Some(entry1)

  override def last: Option[A] = Some(entry4)

  override def init: Option[IDigit[A]] = Some(Digit3(entry1, entry2, entry3))

  override def tail: Option[IDigit[A]] = Some(Digit3(entry2, entry3, entry4))

  override def toList: List[A] = entry1 :: entry2 :: entry3 :: entry4 :: Nil

  override def toTreeComponent: ITreeComponent[A] =
    Deep(Digit2(entry1, entry2), Empty(), Digit2(entry3, entry4))

  override def toString: String =
    s"Digit( ${entry1.toString}, ${entry2.toString}, ${entry3.toString}, ${entry4.toString} )"

  private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] @unchecked => component.size
      case digit: IDigit[A] @unchecked             => digit.size
      case node: INode[A] @unchecked               => node.size
      case _                                       => 1
