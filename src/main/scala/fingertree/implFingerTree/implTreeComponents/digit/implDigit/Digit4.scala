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
import fingertree.implFingerTree.ITreeComponent

final case class Digit4[A](one: A, two: A, three: A, four: A) extends IDigit[A]:
    override def :+[A1 >: A](newEntry: A1): IDigit[A1] = throw new UnsupportedOperationException("Digit4 is already max sized. Can´t be appended!")
  
    override def size: Int =  measureSize(one) + measureSize(two) + measureSize(three) + measureSize(four)

    private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A] => digit.size
      case node: INode[A] => node.size
      case _ => 1

    override def toString: String = s"Digit( ${one.toString}, ${two.toString}, ${three.toString}, ${four.toString} )"
