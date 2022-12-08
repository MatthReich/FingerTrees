package digit.implDigit

import digit.IDigit
import deep.IDeep
import deep.implDeep.Deep
import node.INode
import fingertree.implFingerTree.ITreeComponent

final case class Digit3[A](one: A , two: A, three: A) extends IDigit[A]:

    override def :+[A1 >: A](newEntry: A1): IDigit[A1] = Digit4(one, two, three, newEntry)

    override def size: Int =  measureSize(one) + measureSize(two) + measureSize(three) 

    private def measureSize(entry: A): Int =
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A] => digit.size
      case node: INode[A] => node.size
      case _ => 1

    override def toString: String = s"Digit( ${one.toString}, ${two.toString}, ${three.toString} )"
