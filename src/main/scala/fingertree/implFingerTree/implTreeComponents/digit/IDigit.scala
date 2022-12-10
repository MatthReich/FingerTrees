package digit

import deep.IDeep
import node.INode

trait IDigit[+A]:
  def :+[B >: A](newEntry: B): IDigit[B]
  def +:[B >: A](newEntry: B): IDigit[B]
  
  def size: Int
  def isEmpty: Boolean = false
  def head: Option[A]
  def last: Option[A]

  def toList: List[A]
  def toString: String

