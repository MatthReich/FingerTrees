package digit

trait IDigit[A] {
  def +:(entry: A): IDigit[A]
  def toString: String
}
