package deep.implDeep

import deep.IDeep
import digit.IDigit
import digit.implDigit.Digit1
import digit.implDigit.Digit2
import digit.implDigit.Digit4
import empty.implEmpty.Empty
import fingertree.implFingerTree.ITreeComponent
import node.INode
import node.implNode.Node3
import node.nodeImpl.Node2
import single.implSingle.Single
import view.IView
import view.implView.IViewLeft
import view.implView.IViewRight
import view.implView.implViewLeftCons.ViewLeftCons
import view.implView.implViewRightCons.ViewRightCons

case class Deep[+A](
    prefix: IDigit[A],
    deep: ITreeComponent[INode[A]],
    suffix: IDigit[A]
) extends IDeep[A],
      ITreeComponent[A]:
  override def :+[B >: A](newEntry: B): ITreeComponent[B] =
    suffix match
      case Digit4(entry1, entry2, entry3, entry4) =>
        val newDeep = deep :+ Node3(entry1, entry2, entry3)
        val newSuffix = Digit2(entry4, newEntry)
        this.copy(deep = newDeep, suffix = newSuffix)
      case partialDigit =>
        this.copy(suffix = partialDigit :+ newEntry)

  override def +:[B >: A](newEntry: B): ITreeComponent[B] =
    prefix match
      case Digit4(entry1, entry2, entry3, entry4) =>
        val newPrefix = Digit2(newEntry, entry1)
        val newDeep = Node3(entry2, entry3, entry4) +: deep
        this.copy(prefix = newPrefix, deep = newDeep)
      case partialDigits =>
        this.copy(prefix = newEntry +: partialDigits)

  override def ++[B >: A](treeToConcat: ITreeComponent[B]): ITreeComponent[B] =
    treeToConcat match
      case Empty()           => this
      case Single(entry)     => this :+ entry
      case newDeep: IDeep[B] => concatDeep[B](this, Nil, newDeep)

  override def size: Int = prefix.size + deep.size + suffix.size

  override def isEmpty: Boolean = false

  override def head: Option[A] = prefix.head

  override def last: Option[A] = suffix.last

  override def init: Option[ITreeComponent[A]] = viewRight match
    case None                              => None
    case Some(viewRightRes: IViewRight[A]) => Some(viewRightRes.init)

  override def tail: Option[ITreeComponent[A]] = viewLeft match
    case None                            => None
    case Some(viewLeftRes: IViewLeft[A]) => Some(viewLeftRes.tail)

  override def toList: List[A] =
    prefix.toList ++: deep.toList.flatMap(a => a.toList) ++: suffix.toList ++: Nil

  override def toString: String =
    s"Deep( ${prefix.toString}, ${deep.toString}, ${suffix.toString} )"

  override def viewRight: Option[IViewRight[A]] =
    suffix.last match
      case None => None
      case Some(last) =>
        Some(ViewRightCons(last, deepRight(prefix, deep, suffix.init)))

  override def viewLeft: Option[IViewLeft[A]] =
    prefix.head match
      case None => None
      case Some(head) =>
        Some(ViewLeftCons(head, deepLeft(prefix.tail, deep, suffix)))

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
    (left, right) match
      case (Empty(), _) => 
        concatList.foldRight(right)((a, b) => a +: b)
      case (_, Empty()) => 
        concatList.foldLeft(left)((b, a) => b :+ a)
      case (Single(entry), _) =>
        entry +: concatList.foldRight(right)((a, b) => a +: b)
      case (_, Single(entry)) =>
        concatList.foldLeft(left)((b, a) => b :+ a) :+ entry
      case (leftDeep: IDeep[A], rightDeep: IDeep[A]) =>
        concatDeep[A](leftDeep, concatList, rightDeep)

  private def createNodeCombinations[A](
      entriesAsList: List[A]
  ): List[INode[A]] =
    (entriesAsList: @unchecked) match
      case entry1 :: entry2 :: Nil =>
        Node2(entry1, entry2) :: Nil
      case entry1 :: entry2 :: entry3 :: Nil =>
        Node3(entry1, entry2, entry3) :: Nil
      case entry1 :: entry2 :: entry3 :: entry4 :: Nil =>
        Node2(entry1, entry2) :: Node2(entry3, entry4) :: Nil
      case entry1 :: entry2 :: entry3 :: tail =>
        Node3(entry1, entry2, entry3) :: createNodeCombinations(tail)

  private def deepRight[A](
      prefix: IDigit[A],
      deep: ITreeComponent[INode[A]],
      suffix: Option[IDigit[A]]
  ): ITreeComponent[A] =
    suffix match
      case None =>
        deep.viewRight match
          case Some(ViewRightCons[A](newSuffix, newDeep)) =>
            Deep(prefix, newDeep, Digit1(newSuffix))
          case _ => prefix.toTreeComponent
      case Some(newSuffix) => Deep(prefix, deep, newSuffix)

  private def deepLeft[A](
      prefix: Option[IDigit[A]],
      deep: ITreeComponent[INode[A]],
      suffix: IDigit[A]
  ): ITreeComponent[A] =
    prefix match
      case None =>
        deep.viewLeft match
          case Some(ViewLeftCons[A](newPrefix, newDeep)) =>
            Deep(Digit1(newPrefix), newDeep, suffix)
          case _ => suffix.toTreeComponent
      case Some(newPrefix) => Deep(newPrefix, deep, suffix)
