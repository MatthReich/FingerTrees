package node.implNode

import node.INode

final case class Node3[A](one: A, two: A, three: A) extends INode[A]:
  override def toString: String = s"Node( ${one.toString}, ${two.toString}, ${three.toString} )"
