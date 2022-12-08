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

final case class Deep[+A](
    prefix: IDigit[A],
    deep: ITreeComponent[INode[A]],
    suffix: IDigit[A]
) extends IDeep[A],
      ITreeComponent[A]:
  override def :+[B >: A](newEntry: B): ITreeComponent[B] =
    suffix match {
      case Digit4(entry1, entry2, entry3, entry4) =>
        val treeNew = deep :+ Node3(entry1, entry2, entry3)
        val suffix = Digit2(entry4, newEntry)
        Deep(prefix, treeNew, suffix)
      case partialDigit =>
        Deep(prefix, deep, partialDigit :+ newEntry)
    }

  override def size: Int = prefix.size + deep.size + suffix.size

  override def toString: String =
    s"Deep( ${prefix.toString}, ${deep.toString}, ${suffix.toString} )"
