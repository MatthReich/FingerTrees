package single

import deep.IDeep
import node.INode

trait ISingle[A] {
  def one: A | INode[A]

  def +:(entry: A | INode[A]): IDeep[A]
  def toString: String
}
