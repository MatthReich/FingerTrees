package node

trait INode[+A]:
  def size: Int
  def isEmpty: Boolean

  def toString: String
