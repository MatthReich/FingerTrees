package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode

final case class Digit1[A](one: A | INode[A]) extends IDigit[A]:
    override def +:(entry: A | INode[A]): Option[IDigit[A]] = 
        one match
            case value: A => Some(Digit2(value, entry.asInstanceOf[A]))
            case node: INode[A] => Some(Digit2(node, entry.asInstanceOf[INode[A]]))

    override def toString: String = String.format("Digit( %s )", one.toString())
