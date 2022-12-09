package fingertree

import fingertree.implFingerTree.ITreeComponent

trait IFingerTree[A]:
  def treeHead: ITreeComponent[A]

  def append(entries: A*): IFingerTree[A]
  def prepend(entries: A*): IFingerTree[A]
  def concat(tree: A): IFingerTree[A]

  def size: Int
  def leftHead: A
  def rightHead: A
  def leftTail: A // TODO alles außer linkes Element?
  def rightTail: A

  def toString: String
