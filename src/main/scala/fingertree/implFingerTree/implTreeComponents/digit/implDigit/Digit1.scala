package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode
import fingertree.implFingerTree.ITreeComponent

final case class Digit1[A](entry: A) extends IDigit[A]:
  override def :+[B >: A](newEntry: B): IDigit[B] = Digit2(entry, newEntry)

  override def +:[B >: A](newEntry: B): IDigit[B] = Digit2(newEntry, entry)

  override def size: Int = entry match
    case component: ITreeComponent[A] => component.size
    case digit: IDigit[A]             => digit.size
    case node: INode[A]               => node.size
    case _                            => 1

  override def toString: String = s"Digit( ${entry.toString} )"
