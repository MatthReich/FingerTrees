package digit.implDigit

import digit.IDigit

final case class Digit3[A](one: A, two: A, three: A) extends IDigit[A]:
    override def +:(entry: A): IDigit[A] = Digit4(one, two, three, entry)

    override def toString: String = String.format("Digit( %s, %s, %s )", one.toString(), two.toString(), three.toString())
