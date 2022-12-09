package fingertree.implFingerTree

import fingertree.IFingerTree
import node.INode
import empty.IEmpty
import empty.implEmpty.Empty
import single.ISingle
import single.implSingle.Single
import deep.IDeep
import deep.implDeep.Deep
import fingertree.implFingerTree.ITreeComponent

final case class FingerTree[A](treeHead: ITreeComponent[A] = Empty())
    extends IFingerTree[A]:
  override def append(entries: A*): FingerTree[A] =
    var tmp = treeHead
    entries.toSeq.foreach(e => tmp = tmp.:+[A](e))
    this.copy(treeHead = tmp)

  override def prepend(entries: A*): FingerTree[A] =
    var tmp = treeHead
    entries.toSeq.foreach(e => tmp = tmp.+:[A](e))
    this.copy(treeHead = tmp)

  override def concat(tree: A): FingerTree[A] = ???

  override def size: Int = treeHead.size

  override def isEmpty: Boolean = ???

  override def head: Option[A] = treeHead.head

  override def last: Option[A] = treeHead.last

  override def leftTail: A = ???

  override def rightTail: A = ???

  override def toString(): String = treeHead.toString
