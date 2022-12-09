package digit

import deep.IDeep
import node.INode

trait IDigit[+A]:
  def :+[B >: A](newEntry: B): IDigit[B]
  def +:[B >: A](newEntry: B): IDigit[B]
  
  def size: Int
  def head: Option[A]
  def last: Option[A]

  def toString: String

