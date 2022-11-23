package empty

import single.implSingle.Single
import single.ISingle

trait IEmpty[A] {
  def +:(entry: A): ISingle[A]
  def toString: String
}
