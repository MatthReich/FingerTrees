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

final case class Digit4[A](one: A, two: A, three: A, four: A) extends IDigit[A]:
    override def :+[A1 >: A](newEntry: A1): IDigit[A1] = throw new UnsupportedOperationException("Digit4 is already max sized. Can´t be appended!")

    override def toString: String = s"Digit( ${one.toString}, ${two.toString}, ${three.toString}, ${four.toString} )"
