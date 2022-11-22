package digit.implDigit

import digit.IDigit

final case class Digit4[A](one: A, two: A, three: A, four: A) extends IDigit[A]:
    override def +:(entry: A): IDigit[A] = ???

    override def toString: String = String.format("Digit( %s, %s, %s, %s )", one.toString(), two.toString(), three.toString(), four.toString())
