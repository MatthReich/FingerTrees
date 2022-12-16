package view.implView

import view.IView
import fingertree.implFingerTree.ITreeComponent
import digit.IDigit
import node.INode

trait IViewLeft[+A] extends IView:
  def head: Option[A]
  def tail: ITreeComponent[A]
