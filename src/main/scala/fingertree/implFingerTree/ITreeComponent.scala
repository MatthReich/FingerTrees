package fingertree.implFingerTree

trait ITreeComponent[+A]:
  def :+[B >: A](newEntry: B): ITreeComponent[B]
  def +:[B >: A](newEntry: B): ITreeComponent[B]

  def size: Int
