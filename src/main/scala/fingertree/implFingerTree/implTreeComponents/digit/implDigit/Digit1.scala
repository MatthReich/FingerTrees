package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode

final case class Digit1[A](one: A) extends IDigit[A]:

    override def :+[A1 >: A](newEntry: A1): IDigit[A1] = Digit2(one, newEntry)

    override def toString: String = s"Digit( ${one.toString} )"
