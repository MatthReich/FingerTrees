package empty

import single.implSingle.Single
import single.ISingle
import fingertree.implFingerTree.ITreeComponent

trait IEmpty extends ITreeComponent[Nothing]:
  def toString: String
