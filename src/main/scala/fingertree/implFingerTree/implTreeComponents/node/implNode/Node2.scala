package node.nodeImpl

import node.INode
import fingertree.implFingerTree.ITreeComponent
import digit.IDigit

final case class Node2[A](one: A, two: A) extends INode[A]:
  override def size: Int =  measureSize(one) + measureSize(two)

  private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A] => digit.size
      case node: INode[A] => node.size
      case _ => 1

  override def toString: String = s"Node( ${one.toString}, ${two.toString} )"
