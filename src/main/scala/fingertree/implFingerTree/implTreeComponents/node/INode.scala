package node

trait INode[+A]:
  def toString: String
  def size: Int

