package digit.implDigit

import digit.IDigit

final case class Digit1[A](one: A) extends IDigit[A]:
    override def +:(entry: A): IDigit[A] = Digit2(one, entry)

    override def toString: String = String.format("Digit( %s )", one.toString())
