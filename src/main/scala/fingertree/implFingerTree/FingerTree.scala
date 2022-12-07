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

final case class FingerTree[A](treeHead: ITreeComponent[A]
 = Empty()) extends IFingerTree[A] {
  override def append(entry: A): FingerTree[A] = this.copy(treeHead = treeHead.:+(entry))

  override def remove(entry: A): IFingerTree[A] = ??? 

  override def getIndex(entry: A): Int = ???
  override def get(index: Int): A = ???
  override def set(index: Int, entry: A): IFingerTree[A] = ???

  override def head: A = ???
  override def last: A = ???
  override def toString(): String = treeHead.toString
}
