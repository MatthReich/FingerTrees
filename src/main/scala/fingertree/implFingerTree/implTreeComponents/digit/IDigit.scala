package digit

import deep.IDeep
import node.INode
import fingertree.implFingerTree.ITreeComponent

trait IDigit[+A]:
  def :+[B >: A](newEntry: B): IDigit[B]
  def +:[B >: A](newEntry: B): IDigit[B]
  
  def size: Int
  def isEmpty: Boolean = false
  def head: Option[A]
  def last: Option[A]
  def init: Option[IDigit[A]]
  def tail: Option[IDigit[A]]

  def toList: List[A]
  def toTreeComponent: ITreeComponent[A]
  def toString: String

