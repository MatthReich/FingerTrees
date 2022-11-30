package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode

final case class Digit2[A](one: A  | INode[A], two: A  | INode[A]) extends IDigit[A]:
    override def +:(entry: A | INode[A]): Option[IDigit[A]] = 
        one match
            case value: A => Some(Digit3(value, two.asInstanceOf[A], entry.asInstanceOf[A]))
            case node: INode[A] => Some(Digit3(node, two.asInstanceOf[INode[A]], entry.asInstanceOf[INode[A]]))
    override def toString: String = String.format("Digit( %s, %s )", one.toString(), two.toString())
