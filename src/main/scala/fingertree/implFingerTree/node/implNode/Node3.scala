package node.implNode

import node.INode

final case class Node3[A](one: A, two: A, three: A) extends INode[A]:
  override def toString: String = String.format("Node( %s, %s, %s)", one.toString(), two.toString(), three.toString())
