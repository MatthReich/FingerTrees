package digit.implDigit

import digit.IDigit

final case class Digit2[A](one: A, two: A) extends IDigit[A]:
    override def +:(entry: A): IDigit[A] = Digit3(one, two, entry)

    override def toString: String = String.format("Digit( %s, %s )", one.toString(), two.toString())
