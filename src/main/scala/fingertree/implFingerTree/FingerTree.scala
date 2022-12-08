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
 = Empty()) extends IFingerTree[A]:
  override def append(entries: A*): FingerTree[A] = 
    var tmp = treeHead
    entries.toSeq.foreach((e) => { tmp = tmp.:+(e) })
    this.copy(treeHead = tmp)

  override def size: Int = treeHead.size

  override def toString(): String = treeHead.toString

