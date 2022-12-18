package node.implNode

import node.INode
import fingertree.implFingerTree.ITreeComponent
import digit.IDigit

case class Node3[A](entry1: A, entry2: A, entry3: A) extends INode[A]:
  override def size: Int =
    measureSize(entry1) + measureSize(entry2) + measureSize(entry3)

  override def isEmpty: Boolean = false

  override def toString: String =
    s"Node( ${entry1.toString}, ${entry2.toString}, ${entry3.toString} )"

  private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A]             => digit.size
      case node: INode[A]               => node.size
      case _                            => 1
