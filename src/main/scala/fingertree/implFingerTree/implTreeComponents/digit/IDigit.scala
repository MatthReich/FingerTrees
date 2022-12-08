package digit

import deep.IDeep
import node.INode

trait IDigit[+A]:
  def :+[B >: A](newEntry: B): IDigit[B]
  
  def size: Int

  def toString: String

