package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode

final case class Digit2[A](one: A , two: A) extends IDigit[A]:
    
    override def :+[A1 >: A](newEntry: A1): IDigit[ A1] = Digit3(one, two, newEntry)

    override def toString: String = String.format("Digit( %s, %s )", one.toString(), two.toString())
