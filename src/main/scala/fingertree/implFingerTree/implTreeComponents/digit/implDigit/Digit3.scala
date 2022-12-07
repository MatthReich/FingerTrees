package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode

final case class Digit3[A](one: A , two: A, three: A) extends IDigit[A]:

    override def :+[A1 >: A](newEntry: A1): IDigit[A1] = Digit4(one, two, three, newEntry)

    override def toString: String = s"Digit( ${one.toString}, ${two.toString}, ${three.toString} )"
