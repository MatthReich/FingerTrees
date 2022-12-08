package single.implSingle

import single.ISingle
import deep.IDeep
import deep.implDeep.Deep
import digit.implDigit.Digit1
import empty.implEmpty.Empty
import node.INode
import fingertree.implFingerTree.ITreeComponent
import digit.IDigit

final case class Single[A](entry: A) extends ISingle[A], ITreeComponent[A]:

  override def :+[A1 >: A](newEntry: A1): ITreeComponent[A1] =
      val prefix  = Digit1[A](entry)
      val suffix  = Digit1[A1](newEntry)
      Deep(prefix, Empty(), suffix)

  override def size: Int = 
    entry match
      case component: ITreeComponent[A] => component.size
      case digit: IDigit[A] => digit.size
      case node: INode[A] => node.size
      case _ => 1

  override def toString: String = s"Single( ${entry.toString()} )"
