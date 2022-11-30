package node.implNode

import node.INode

final case class Node3[A](one: A | INode[A], two: A | INode[A], three: A | INode[A]) extends INode[A]:
  override def toString: String = String.format("Node( %s, %s, %s)", one.toString(), two.toString(), three.toString())
