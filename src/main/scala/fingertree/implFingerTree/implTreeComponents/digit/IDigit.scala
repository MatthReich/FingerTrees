package digit

import deep.IDeep
import node.INode

trait IDigit[+A]:
  def :+[A1 >: A](newEntry: A1): IDigit[A1]
  
  def size: Int

  def toString: String

