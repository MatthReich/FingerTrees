package deep

import digit.IDigit
import empty.IEmpty
import single.ISingle
import node.INode

trait IDeep[A] {
  def one: IDigit[A]
  def two: IEmpty[A] | IDeep[A] | ISingle[A]
  def three: IDigit[A]

  def +:(entry: A): IDeep[A]
  def toString: String
}
