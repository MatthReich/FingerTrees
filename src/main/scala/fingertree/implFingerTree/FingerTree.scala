package fingertree.implFingerTree

import fingertree.IFingerTree
import node.INode
import empty.IEmpty
import empty.implEmpty.Empty
import single.implSingle.Single
import deep.IDeep
import deep.implDeep.Deep
import fingertree.implFingerTree.ITreeComponent

final case class FingerTree[A](treeHead: ITreeComponent[A] = Empty())
    extends IFingerTree[A]:
  override def append(entries: A*): FingerTree[A] =
    var tmp = treeHead
    entries.foreach(e => tmp = tmp.:+[A](e))
    this.copy(treeHead = tmp)

  override def prepend(entries: A*): FingerTree[A] =
    var tmp = treeHead
    entries.foreach(e => tmp = tmp.+:[A](e))
    this.copy(treeHead = tmp)

  override def concat(tree: IFingerTree[A]): FingerTree[A] =
    this.copy(treeHead = treeHead ++ tree.treeHead)

  override def size: Int = treeHead.size

  override def isEmpty: Boolean = treeHead.isEmpty

  override def head: Option[A] = treeHead.head

  override def last: Option[A] = treeHead.last

  override def init: Option[FingerTree[A]] =
    treeHead.init match
      case None       => None
      case Some(init) => Some(this.copy(treeHead = init))

  override def tail: Option[FingerTree[A]] =
    treeHead.tail match
      case None       => None
      case Some(tail) => Some(this.copy(treeHead = tail))

  override def toList: List[A] = treeHead.toList

  override def toString(): String = treeHead.toString
