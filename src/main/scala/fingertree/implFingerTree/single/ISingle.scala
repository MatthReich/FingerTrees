package single

import deep.IDeep

trait ISingle[A] {
  def +:(entry: A): IDeep[A]
  def toString: String
}
