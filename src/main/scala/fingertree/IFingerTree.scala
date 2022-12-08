package fingertree

import fingertree.implFingerTree.ITreeComponent

trait IFingerTree[A]:
  def treeHead: ITreeComponent[A]

  def append(entries: A*): IFingerTree[A]
 
  def head: A
  def last: A
  def length: Int

  def toString: String

