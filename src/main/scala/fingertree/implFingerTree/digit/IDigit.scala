package digit

import deep.IDeep
import node.INode

trait IDigit[A] {
  def +:(entry: A | INode[A]): Option[IDigit[A]]
  def toString: String
}
