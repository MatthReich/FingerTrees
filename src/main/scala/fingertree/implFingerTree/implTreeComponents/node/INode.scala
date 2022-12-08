package node

trait INode[+A]:
  def size: Int

  def toString: String
