package node.nodeImpl

import node.INode

final case class Node2[A](one: A, two: A) extends INode[A]:

  override def toString: String = s"Node( ${one.toString}, ${two.toString} )"
