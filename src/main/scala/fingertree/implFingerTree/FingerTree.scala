package fingertree.implFingerTree

import fingertree.IFingerTree
import node.INode
import empty.IEmpty
import empty.implEmpty.Empty
import single.ISingle
import single.implSingle.Single
import deep.IDeep
import deep.implDeep.Deep

final case class FingerTree[A](treeHead: IEmpty[A] | ISingle[A] | IDeep[A] = Empty[A]()) extends IFingerTree[A] {
  override def append(entry: A): FingerTree[A] = 
    treeHead match // FIXME better case check
      case Empty[A]() => this.copy(treeHead.asInstanceOf[IEmpty[A]].+:(entry))
      case Single[A](one) => this.copy(treeHead.asInstanceOf[ISingle[A]].+:(entry))
      case Deep[A](one, two, three) => this.copy(treeHead.asInstanceOf[IDeep[A]].+:(entry))
      case _ => this.copy() // TODO error handling

  override def remove(entry: A): IFingerTree[A] = ??? 

  override def getIndex(entry: A): Int = ???
  override def get(index: Int): A = ???
  override def set(index: Int, entry: A): IFingerTree[A] = ???

  override def head: A = ???
  override def last: A = ???
  override def toString(): String = treeHead.toString
}
