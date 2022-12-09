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
        val newDeep = deep.:+[INode[A]](Node3(entry1, entry2, entry3))
        val newSuffix = Digit2(entry4, newEntry)
        Deep(prefix, newDeep, newSuffix)
      case partialDigit =>
        Deep(prefix, deep, partialDigit :+ newEntry)
    }

  override def +:[B >: A](newEntry: B): ITreeComponent[B] =
    prefix match {
      case Digit4(entry1, entry2, entry3, entry4) =>
        val newPrefix = Digit2(newEntry, entry1)
        val newDeep = deep.+:[INode[A]](Node3(entry2, entry3, entry4))
        Deep(newPrefix, newDeep, suffix)
      case partialDigits =>
        Deep(newEntry +: partialDigits, deep, suffix)
    }

  override def size: Int = prefix.size + deep.size + suffix.size

  override def head: Option[A] = prefix.head

  override def last: Option[A] = suffix.last

  override def toString: String =
    s"Deep( ${prefix.toString}, ${deep.toString}, ${suffix.toString} )"
