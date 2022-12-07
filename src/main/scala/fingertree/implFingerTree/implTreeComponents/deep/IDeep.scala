package deep

import digit.IDigit
import empty.IEmpty
import single.ISingle
import node.INode
import fingertree.implFingerTree.ITreeComponent

trait IDeep[+A] extends ITreeComponent[A] {
  def prefix: IDigit[A]
  def tree: ITreeComponent[INode[A]]
  def suffix: IDigit[A]

  def toString: String
}
