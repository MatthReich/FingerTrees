import fingertree.implFingerTree.FingerTree

val intFingerTree: FingerTree[Int]  = FingerTree[Int]()

val oneParamFingerTree = intFingerTree append 1
val nParamFingerTree   = intFingerTree prepend (1, 2, 3, 4)
val seqParamFingerTree = intFingerTree.append((1 to 50).toSeq: _*)

val concatenatedFingerTree = nParamFingerTree concat seqParamFingerTree

val head: Option[Int] = nParamFingerTree.head
val last: Option[Int] = nParamFingerTree.last
val init: Option[FingerTree[Int]] = nParamFingerTree.init
val tail: Option[FingerTree[Int]] = nParamFingerTree.tail

val size: Int = seqParamFingerTree.size

val isFingerTreeEmpty: Boolean = intFingerTree.isEmpty

val seqParamFingerTreeAsList: List[Int] = seqParamFingerTree.toList
