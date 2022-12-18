package view.implView

import view.IView
import fingertree.implFingerTree.ITreeComponent

trait IViewRight[+A] extends IView:
    def last: A
    def init: ITreeComponent[A]
