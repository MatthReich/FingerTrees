package node.nodeImpl

import digit.IDigit
import fingertree.implFingerTree.ITreeComponent
import node.INode

case class Node2[A](entry1: A, entry2: A) extends INode[A]:
  override def size: Int = measureSize(entry1) + measureSize(entry2)

  override def isEmpty: Boolean = false

  override def toList: List[A] = entry1 :: entry2 :: Nil

  override def toString: String =
    s"Node( ${entry1.toString}, ${entry2.toString} )"

  private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] @unchecked => component.size
      case digit: IDigit[A] @unchecked             => digit.size
      case node: INode[A] @unchecked               => node.size
      case _                                       => 1
