package fingertree

import fingertree.implFingerTree.ITreeComponent

trait IFingerTree[A]:
  def treeHead: ITreeComponent[A]

  def append(entries: A*): IFingerTree[A] // TODO wegen append/prepend auf Digit4 auf Try[Success | Failure] umstellen?
  def prepend(entries: A*): IFingerTree[A]
  def concat(tree: IFingerTree[A]): IFingerTree[A]
  // TODO remove / pop gewünscht?

  def size: Int
  def isEmpty: Boolean
  def head: Option[A]
  def last: Option[A]
  def leftTail: Option[A] // TODO alles außer linkes Element?
  def rightTail: Option[A]
  // TODO at index?

  def toString: String
