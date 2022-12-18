package fingertree

import fingertree.implFingerTree.ITreeComponent

trait IFingerTree[A]:
  def treeHead: ITreeComponent[A]

  def append(entries: A*): IFingerTree[A]
  def prepend(entries: A*): IFingerTree[A]
  def concat(tree: IFingerTree[A]): IFingerTree[A]

  def size: Int
  def isEmpty: Boolean
  def head: Option[A]
  def last: Option[A]
  def init: Option[IFingerTree[A]]
  def tail: Option[IFingerTree[A]]

  def toString: String
