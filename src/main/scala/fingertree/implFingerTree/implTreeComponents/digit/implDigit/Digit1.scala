package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode
import fingertree.implFingerTree.ITreeComponent
import single.implSingle.Single

case class Digit1[A](entry: A) extends IDigit[A]:
  override def :+[B >: A](newEntry: B): IDigit[B] = Digit2(entry, newEntry)

  override def +:[B >: A](newEntry: B): IDigit[B] = Digit2(newEntry, entry)

  override def size: Int = entry match
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

  override def init: Option[IDigit[A]] = None
  
  override def tail: Option[IDigit[A]] = None

  override def toList: List[A] = entry :: Nil

  override def toTreeComponent: ITreeComponent[A] = Single(entry)

  override def toString: String = s"Digit( ${entry.toString} )"
