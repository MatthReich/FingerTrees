package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import single.implSingle.Single
import node.implNode.Node3
import empty.implEmpty.Empty
import single.ISingle
import empty.IEmpty
import node.INode
import fingertree.implFingerTree.ITreeComponent

final case class Digit4[A](entry1: A, entry2: A, entry3: A, entry4: A)
    extends IDigit[A]:
  override def :+[B >: A](newEntry: B): IDigit[B] =
    throw new UnsupportedOperationException(
      "Digit4 is already max sized. Can´t be appended!"
    )

  override def size: Int =
    measureSize(entry1) + measureSize(entry2) + measureSize(entry3) + measureSize(entry4)

  private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A]             => digit.size
      case node: INode[A]               => node.size
      case _                            => 1

  override def toString: String =
    s"Digit( ${entry1.toString}, ${entry2.toString}, ${entry3.toString}, ${entry4.toString} )"
