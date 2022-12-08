package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode
import fingertree.implFingerTree.ITreeComponent

final case class Digit2[A](entry1: A, entry2: A) extends IDigit[A]:
  override def :+[B >: A](newEntry: B): IDigit[B] =
    Digit3(entry1, entry2, newEntry)

  override def size: Int = measureSize(entry1) + measureSize(entry2)

  private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A]             => digit.size
      case node: INode[A]               => node.size
      case _                            => 1

  override def toString: String =
    s"Digit( ${entry1.toString}, ${entry2.toString} )"
