package fingertree.implFingerTree

trait ITreeComponent[+A]:
  def :+[A1 >: A](newEntry: A1): ITreeComponent[A1]
  def size: Int

