package deep.implDeep

import deep.IDeep
import digit.IDigit
import empty.IEmpty
import single.ISingle
import node.INode
import node.implNode.Node3
import single.implSingle.Single
import digit.implDigit.Digit4
import digit.implDigit.Digit2
import digit.implDigit.Digit1
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import digit.implDigit.Digit3

final case class Deep[+A](prefix: IDigit[A], tree: ITreeComponent[INode[A]], suffix: IDigit[A]) extends IDeep[A], ITreeComponent[A]:

  override  def :+[A1 >: A](newEntry: A1): ITreeComponent[A1] = {
    suffix match {
      case Digit4(one, two, three, four) =>
        val treeNew     = tree.:+[INode[A1]](Node3(one, two, three))
        val suffix      = Digit2(four, newEntry)
        Deep(prefix, treeNew, suffix)
      case partialDigit =>
        Deep(prefix, tree, partialDigit :+ newEntry)
    }
  }

  override def toString: String = String.format(
    "Deep( %s, %s, %s )",
    prefix.toString,
    tree.toString,
    suffix.toString
  )
