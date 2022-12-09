package node

trait INode[+A]:
  def size: Int
  def isEmpty: Boolean = false
  def head: Option[A]
  def last: Option[A]

  def toString: String
