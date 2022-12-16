package fingertree.implFingerTree.implTreeComponents.view.implView.implViewLeftCons

import digit.IDigit
import fingertree.implFingerTree.ITreeComponent
import view.implView.IViewLeft
import node.INode

final case class ViewLeftCons[A](head: Option[A], tail: ITreeComponent[A]) extends IViewLeft[A]:
    override def isEmpty = false
