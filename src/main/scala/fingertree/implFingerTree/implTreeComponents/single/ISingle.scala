package single

import deep.IDeep
import node.INode
import fingertree.implFingerTree.ITreeComponent

trait ISingle[A] extends ITreeComponent[A]:
  def entry: A

  def toString: String
