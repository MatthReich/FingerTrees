package node

trait INode[+A]:
  def size: Int
  def isEmpty: Boolean

  def toList: List[A]
  def toString: String
