package fingertree.implFingerTree

import fingertree.IFingerTree
import node.INode
import empty.IEmpty

class FingerTree[A](treeHead: IEmpty) extends IFingerTree[A] {
  override def append(entry: A): FingerTree[A] = ???

  override def remove(entry: A): IFingerTree[A] = ??? 

  override def getIndex(entry: A): Int = ???
  override def get(index: Int): A = ???
  override def set(index: Int, entry: A): IFingerTree[A] = ???

  override def head: A = ???
  override def last: A = ???
  override def toString(): String = "FingerTree"


}
