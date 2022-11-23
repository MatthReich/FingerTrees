package deep

trait IDeep[A] {
  def +:(entry: A): IDeep[A]
  def toString: String
}
