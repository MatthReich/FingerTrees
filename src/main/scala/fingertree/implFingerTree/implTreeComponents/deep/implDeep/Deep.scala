package deep.implDeep

import deep.IDeep
import digit.IDigit
import empty.IEmpty
import single.ISingle
import node.INode
import node.implNode.Node3
import single.implSingle.Single
import digit.implDigit.Digit4
import digit.implDigit.Digit2
import digit.implDigit.Digit1
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import digit.implDigit.Digit3
import node.nodeImpl.Node2

final case class Deep[+A](
    prefix: IDigit[A],
    deep: ITreeComponent[INode[A]],
    suffix: IDigit[A]
) extends IDeep[A],
      ITreeComponent[A]:
  override def :+[B >: A](newEntry: B): ITreeComponent[B] =
    suffix match {
      case Digit4(entry1, entry2, entry3, entry4) =>
        val newDeep = deep.:+[INode[A]](Node3(entry1, entry2, entry3))
        val newSuffix = Digit2(entry4, newEntry)
        Deep(prefix, newDeep, newSuffix)
      case partialDigit =>
        Deep(prefix, deep, partialDigit :+ newEntry)
    }

  override def +:[B >: A](newEntry: B): ITreeComponent[B] =
    prefix match {
      case Digit4(entry1, entry2, entry3, entry4) =>
        val newPrefix = Digit2(newEntry, entry1)
        val newDeep = deep.+:[INode[A]](Node3(entry2, entry3, entry4))
        Deep(newPrefix, newDeep, suffix)
      case partialDigits =>
        Deep(newEntry +: partialDigits, deep, suffix)
    }

  override def ++[B >: A](treeToConcat: ITreeComponent[B]): ITreeComponent[B] =
    treeToConcat match
      case Empty()           => this
      case Single(entry)     => this :+ entry
      case newDeep: IDeep[A] => concatDeep[B](this, Nil, newDeep)

  override def size: Int = prefix.size + deep.size + suffix.size

  override def isEmpty: Boolean = false

  override def head: Option[A] = prefix.head

  override def last: Option[A] = suffix.last

  override def init: Option[ITreeComponent[A]] = ???

  override def tail: Option[ITreeComponent[A]] = ???

  override def toString: String =
    s"Deep( ${prefix.toString}, ${deep.toString}, ${suffix.toString} )"

  private def concatDeep[A](
      thisDeep: IDeep[A],
      concatList: List[A],
      deepToConcat: IDeep[A]
  ): ITreeComponent[A] =
    val newPrefix = thisDeep.prefix
    val newDeep = concatNewDeep(
      thisDeep.deep,
      createNodeCombinations(
        thisDeep.suffix.toList ::: concatList ::: deepToConcat.prefix.toList
      ),
      deepToConcat.deep
    )
    val newSuffix = deepToConcat.suffix
    Deep(newPrefix, newDeep, newSuffix)

  private def concatNewDeep[A](
      left: ITreeComponent[A],
      concatList: List[A],
      right: ITreeComponent[A]
  ): ITreeComponent[A] =
    (left, right) match {
      case (Empty(), _) => concatList.foldRight(right)((a, b) => a +: b)
      case (_, Empty()) => concatList.foldLeft(left)((b, a) => b :+ a)
      case (Single(entry), _) =>
        entry +: concatList.foldRight(right)((a, b) => a +: b)
      case (_, Single(entry)) =>
        concatList.foldLeft(left)((b, a) => b :+ a) :+ entry
      case (leftDeep @ Deep(_, _, _), rightDeep @ Deep(_, _, _)) =>
        concatDeep[A](leftDeep, concatList, rightDeep)
    }

  private def createNodeCombinations[A](
      entriesAsList: List[A]
  ): List[INode[A]] =
    (entriesAsList: @unchecked) match {
      case entry1 :: entry2 :: Nil => Node2(entry1, entry2) :: Nil
      case entry1 :: entry2 :: entry3 :: Nil =>
        Node3(entry1, entry2, entry3) :: Nil
      case entry1 :: entry2 :: entry3 :: entry4 :: Nil =>
        Node2(entry1, entry2) :: Node2(entry3, entry4) :: Nil
      case entry1 :: entry2 :: entry3 :: tail =>
        Node3(entry1, entry2, entry3) :: createNodeCombinations(tail)
    }
