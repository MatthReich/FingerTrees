package node

trait INode[+A]:
  def size: Int
  def head: Option[A]
  def last: Option[A]

  def toString: String
