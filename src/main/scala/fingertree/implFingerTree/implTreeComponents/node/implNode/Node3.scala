package node.implNode

import node.INode
import fingertree.implFingerTree.ITreeComponent
import digit.IDigit

final case class Node3[A](one: A, two: A, three: A) extends INode[A]:

  override def size: Int =  measureSize(one) + measureSize(two) + measureSize(three)

  private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A] => digit.size
      case node: INode[A] => node.size
      case _ => 1

  override def toString: String = s"Node( ${one.toString}, ${two.toString}, ${three.toString} )"
