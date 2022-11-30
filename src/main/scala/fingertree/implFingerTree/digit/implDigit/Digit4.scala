package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import single.implSingle.Single
import node.implNode.Node3
import empty.implEmpty.Empty
import single.ISingle
import empty.IEmpty
import node.INode

final case class Digit4[A](one: A  | INode[A], two: A  | INode[A], three: A | INode[A], four: A  | INode[A]) extends IDigit[A]:
    override def +:(entry: A | INode[A]): Option[IDigit[A]] = None

    override def toString: String = String.format("Digit( %s, %s, %s, %s )", one.toString(), two.toString(), three.toString(), four.toString())
