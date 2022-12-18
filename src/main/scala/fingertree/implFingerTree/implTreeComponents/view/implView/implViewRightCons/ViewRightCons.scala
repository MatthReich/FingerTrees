package view.implView.implViewRightCons

import view.implView.IViewRight
import fingertree.implFingerTree.ITreeComponent

final case class ViewRightCons[A](last: A, init: ITreeComponent[A])
    extends IViewRight[A]:
  override def isEmpty: Boolean = false
