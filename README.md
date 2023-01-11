# FingerTrees

[![Build Status](https://app.travis-ci.com/MatthReich/FingerTrees.svg?branch=master)](https://app.travis-ci.com/MatthReich/FingerTrees) [![Coverage Status](https://coveralls.io/repos/github/MatthReich/FingerTrees/badge.svg?branch=master)](https://coveralls.io/github/MatthReich/FingerTrees?branch=master)

Dieses Projekt entstand im Zuge des Seminars aus dem Informatik Master (INFM) der Hochschule Offenburg. Dabei war es die Aufgabe, einen FingerTree zu implementieren, wobei nur Basisoperationen unterstützt werden sollen und nicht alle möglichen Operationen. Im Folgenden wird auf die Struktur, Benutzung und Implementierung bezüglich dieses Seminars eingegangen.

## Allgemein

FingerTrees sind eine effiziente funktionale Datenstruktur. Sie sind eine spezielle Art von binären Baumstrukturen, die dazu dienen, Sequenzen von Elementen zu speichern und schnellen Zugriff auf diese Elemente zu ermöglichen

Dabei versteht man unter einer funktionalen Datenstruktur eine Datenstruktur, die in einem funktionalen Programmierparadigma implementiert wurde. Im Gegensatz zu imperativen Datenstrukturen, die mithilfe von Anweisungen verändert werden, werden funktionale Datenstrukturen durch die Rückgabe neuer Datenstrukturen von Funktionen verändert. Dies bedeutet, dass funktionale Datenstrukturen nicht mutierbar sind und keine Seiteneffekte haben. Die Daten, welche nach der Veränderung gleich bleiben, werden dabei nicht kopiert, sondern über Referenzen mehrfach verwendet.

Die Effizienz dieser Datenstruktur soll vor allem in dem vorne und hinten Anfügen von Elementen (O(log n), bis zu O(1)), dem Zusammenführen von zwei FingerTrees (O(log n)) und auch dem Zugriff auf das erste bzw. letzte Element (O(1)) gewährleistet sein.

Bei der Implementierung wurde Bezug auf das Paper von [Ralf Hinze und Ross Paterson](https://www.staff.city.ac.uk/~ross/papers/FingerTree.pdf) genommen.

## Struktur

Eine Übersicht der Struktur des Projekts, um die wichtigsten Dateien für das Seminar zu finden, wird folgend gegeben.

```
.
├── documents
│   └── FingerTrees.pptx                        # Präsentation über FingerTrees
├── src
│   ├── main/scala/fingertree/
│   │   ├── Worksheet.worksheet.sc              # Worksheet um FingerTree manuell auszuprobieren
│   │   └── ...                                 # Implementierung der Datenstruktur FingerTrees
│   └── test/scala/fingertree/implFingerTree/
│       └── ...                                 # Die Tests für FingerTrees
└── README.md                                   # Die mit abzugebende textuelle Erklärung
```

## Benutzung

Einen FingerTree erzeugt man wie folgt:

```scala
val intFingerTree: FingerTree[Int]  = FingerTree[Int]()
```

Möchte man Elemente hinzufügen, so ist dies über `append` oder `prepend` möglich. Dabei kann 1 Argument, n Argumente oder eine Sequenz übergeben werden.

```scala
val oneParamFingerTree = intFingerTree append 1
val nParamFingerTree   = intFingerTree prepend (1, 2, 3, 4)
val seqParamFingerTree = intFingerTree.append((1 to 50).toSeq: _*)
```

Wenn man zwei FingerTrees miteinander kombinieren möchte, ist dies über die Funktion `concat` möglich.

```scala
val concatenatedFingerTree = nParamFingerTree concat seqParamFingerTree
```

`head`, `last`, `init` als auch `tail` geben jeweils ein `Option` des Wertes raus, um ein einfacheres Handling zu ermöglichen.

```scala
val head: Option[Int] = nParamFingerTree.head
val last: Option[Int] = nParamFingerTree.last
val init: Option[FingerTree[Int]] = nParamFingerTree.init
val tail: Option[FingerTree[Int]] = nParamFingerTree.tail
```

Auch ist es möglich, die `size` des FingerTree ausgeben zu lassen.

```scala
val size: Int = seqParamFingerTree.size
```

Für die Überprüfung, ob der Baum `empty` ist, wird `isEmpty` benutzt.

```scala
val isFingerTreeEmpty: Boolean = intFingerTree.isEmpty
```

Zuletzt ist es auch Möglich, den Baum als Liste, über `toList`, zurückzubekommen.

```scala
val seqParamFingerTreeAsList: List[Int] = seqParamFingerTree.toList
```

In dem im Projekt enthaltenen [Worksheet](https://github.com/MatthReich/FingerTrees/blob/master/src/main/scala/fingertree/Worksheet.worksheet.sc) sind diese Operationen nochmals aufgeführt und selbst testbar, ohne eine Main-Klasse zu definieren.

## Implementierung

Folgend wird auf die Implementierung eingegangen, was die wesentlichen Punkte des Codes erläutert.

### Struktur

Die Struktur der Implementierung des FingerTrees und der einzelnen Komponenten ist wie folgt:

```
├── ...
├── src/main/scala/fingertree/
│   ├── implFingerTree
│   │   ├── implTreeComponents
│   │   │   ├── deep                    # Implementierung von der Komponente Deep
│   │   │   │   └── ...
│   │   │   ├── digit                   # Implementierung von der Komponente Digit
│   │   │   │   └── ...
│   │   │   ├── empty                   # Implementierung von der Komponente Empty
│   │   │   │   └── ...
│   │   │   ├── node                    # Implementierung von der Komponente Node
│   │   │   │   └── ...
│   │   │   ├── single                  # Implementierung von der Komponente Single
│   │   │   │   └── ...
│   │   │   └── view                    # Implementierung von der Hilfskomponente View, welche für init bzw. tail benötigt wird
│   │   │       └── ...
│   │   ├── FingerTree.scala            # Die Hauptimplementierung des FingerTrees, welche auf die Komponenten zugreift
│   │   └── ITreeComponent.scala
│   └── IFingerTree.scala               # Interface der FingerTrees, welches alle möglichen Operationen beinhaltet
└── ...
```

### Allgemeine Syntax

Im Zuge der Implementierung kommen Generics, für die Typenunabhängigkeit, zum Einsatz. Dabei steht `A` oder `B`, typisch nach der [Dokumentation von Scala](https://docs.scala-lang.org/scala3/book/types-generics.html), für den Typparameter. Der Typ bei zum Beispiel Listen wird generell in `[]` angefügt, also wäre eine Liste vom Typ `A` `List[A]`.

Mit `A*` symbolisiert man `varargs`, also eine variable Anzahl von Argumenten. Dabei kann man diese auf unterschiedliche Art und Weisen nutzen. So kann man einfach mehrere Argumente der Funktion `def f(args: A*)` übergeben, wie zum Beispiel `f(1, 2, 3, 4)`. Es ist aber auch Möglich eine Sequenz `val seq = Seq(1, 2, 3, 4)` an die Funktion zu übergeben `f(seq: _*)`.

Unter `+A` versteht man Kovarianz. Die Verwendung von `B >: A` ermöglicht eine lower bound zu nutzen. Dabei wird in diesem Fall festgelegt, dass `B` ein Supertype von `A` sein muss. Der Vollständigkeit wegen wird noch `-A` erwähnt, was für die Kontravarianz steht, aber nicht verwendet wird.

### append, prepend

Wenn ein Element dem FingerTree hinzugefügt wird, wird dies auf die root-Komponente `treeHead` weitergegeben. Dabei wird im Falle eines `Empty` ein `Single` erzeugt und im Falle eines `Single` ein `Deep`. Jenachdem ob `append` oder `prepend` benutzt wird, mit dem neuen Element als Suffix oder Präfix des Deeps. Im `Deep` wird im Falle des `append` das Suffix betrachtet, ob es ein `Digit4` speichert. Dies ist in dem Folgenden Codeabschnitt zu erkennen.

```scala
suffix match
  case Digit4(entry1, entry2, entry3, entry4) =>
    val newDeep = deep.:+[INode[A]](Node3(entry1, entry2, entry3))
    val newSuffix = Digit2(entry4, newEntry)
    this.copy(deep = newDeep, suffix = newSuffix)
  case partialDigit =>
    this.copy(suffix = partialDigit :+ newEntry)
```

Falls es ein `Digit4` ist, wird ein neues `Deep` erzeugt, welches die ersten drei Elemente des `Digit4` als `Node3` der nächsten Ebene (deep) weitergibt. Das Suffix beinhaltet ein `Digit2` welches das letzte Element des `Digit4` und den neuen Eintrag beinhaltet. Anschließend wird eine Kopie von sich selbst mit den beiden neu erzeugten Elementen zurück gegeben. Falls kein `Digit4` gespeichert war, wird einfach das `Digit` um das neue Element erweitert. Für `prepend` funktioniert das ganze genau gleich, nur Spiegelverkehrt.

### concat

Um den Text einfacher zu halten wird von FingerTree A, auf welchen FingerTree B verschmolzen wird gesprochen. Wenn A leer ist, wird einfach B zurückgegeben. Wenn A nur ein Element speichert, wird die root-Komponente von B mit `prepend` an dieses Element angefügt. Falls A ein Deep ist, wird nun dort geschaut ob B ein `Empty` ist. Wenn ja, wird A zurückgegeben. Bei einem `Single` wird B an A mit `append` angefügt. Die eigentliche Komplexität des Verschmelzens passiert, wenn A und B beide `Deep` sind. Dabei wird eine Hilfsmethode `concatDeep` aufgerufen, welche eine Liste entgegennimmt und die beiden FingerTrees.

```scala
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
```

Dabei ist zu erkennen, dass von A das Präfix und von B das Suffix genommen wird. Das neue deep wird durch `concatNewDeep` bestimmt. Dabei wird außerdem das Suffix aus A, das Präfix von B und der aktuellen Liste, welche Anfangs leer ist, aber mit der Tiefe des FingerTree größer wird, zu einer Liste von `Node` in `createNodeCombinations` erstellt. Zuerst wird das `concatNewDeep` betrachtet.

```scala
private def concatNewDeep[A](
    left: ITreeComponent[A],
    concatList: List[A],
    right: ITreeComponent[A]
): ITreeComponent[A] =
  (left, right) match
    case (Empty(), _) => concatList.foldRight(right)((a, b) => a +: b)
    case (_, Empty()) => concatList.foldLeft(left)((b, a) => b :+ a)
    case (Single(entry), _) =>
      entry +: concatList.foldRight(right)((a, b) => a +: b)
    case (_, Single(entry)) =>
      concatList.foldLeft(left)((b, a) => b :+ a) :+ entry
    case (leftDeep @ Deep(_, _, _), rightDeep @ Deep(_, _, _)) =>
      concatDeep[A](leftDeep, concatList, rightDeep)
```

Hier ist zu erkennen, dass, solange nicht eine Hälfte des deep ein `Empty` oder `Single` ist, dies beiden deeps erneut an das `concatDeep` übergeben werden, wodurch das `concar` eine Ebene weiter unten berechnet wird. Wenn jedoch ein `Empty` oder `Single` enthalten sind, dann werden, wie zuvor schon beschrieben, diese Elemente einfach mit `append` oder `prepend` zusammen geführt. In diesem Fall mit der Besonderheit, dass es eine Liste von Elementen ist, welche zuerst noch mit `foldRight` oder `foldLeft`, also ob das verbleibende deep rechts oder links der Liste von `Node` angefügt werden soll. Dabei ist auch zu erkennen, dass die fold-Funktionen zwei Parameterlisten übergeben bekommen. Das ist vor allem Notwendig für die Typinferenz. Genaueres kann in der [Scala-Dokumentation](https://docs.scala-lang.org/tour/multiple-parameter-lists.html) gelesen werden. Die Liste von `Node` wird wie Folgt erstellt.

```scala
private def createNodeCombinations[A](
    entriesAsList: List[A]
): List[INode[A]] =
  (entriesAsList: @unchecked) match
    case entry1 :: entry2 :: Nil => Node2(entry1, entry2) :: Nil
    case entry1 :: entry2 :: entry3 :: Nil =>
      Node3(entry1, entry2, entry3) :: Nil
    case entry1 :: entry2 :: entry3 :: entry4 :: Nil =>
      Node2(entry1, entry2) :: Node2(entry3, entry4) :: Nil
    case entry1 :: entry2 :: entry3 :: tail =>
      Node3(entry1, entry2, entry3) :: createNodeCombinations(tail)
```

Dabei wird die Liste auf Anzahl Elemente überprüft. Die `::` Notation ist in diesem Fall einfach Schreibweise der Trennung der Elemente. Da es sich um eine einfach verkettete Liste handelt, wird am Ende die Liste durch ein `Nil` beendet. Genaueres in der [Scala-Dokumentation](https://docs.scala-lang.org/overviews/scala-book/list-class.html). Falls nun zwei Elemente in der Liste sind, kann ein `Node2` erzeugt werden. Bei drei Elementen ein `Node3` und vier können zwei `Node2` erzeugt werden. Wenn jedoch mehr als vier Elemente enthalten sind, dann wird ein `Node3` aus den ersten drei Elementen erzeugt und nochmals `createNodeCombinations` aufgerufen, solange bis es maximal noch vier Elemente hat.

### head, last

Bei einem leeren Baum wird als erstes bzw. letztes Element `None` zurückgegeben, da keins existiert. Im Falle von nur einem gespeicherten Element wird dieses als `head` oder `last` gesehen. Um bei einem `Deep` an das erste Element zu kommen, wird von diesem auf das Präfix `head` aufgerufen, welches dann das erste Element des gespeicherten `Digit` liefert. Wenn das letzte Element gefragt ist, wird auf dem Suffix `last` gefragt und das letzte Element des `Digit` zurückgegeben.

### init, tail

Wie auch bei `head` bzw. `last` wird bei einem leeren Baum `None` zurückgegeben. Bei einem `Single` erhält man ein `Empty`. Im Falle eines `Deep` wird bei `init` über eine `View` alles rechts des deep angeschaut, also das Suffix.

```scala
override def viewRight: Option[IViewRight[A]] =
  suffix.last match
    case None => None
    case Some(last) =>
      Some(ViewRightCons(last, deepRight(prefix, deep, suffix.init)))
```

Dafür wird ein Hilfsdatentyp `ViewRightCons` benutzt, welcher das letzte Element, sowie das geforderte `init` enthält. Das `deepRight` erstellt dabei das `init`.

```scala
private def deepRight[A](
    prefix: IDigit[A],
    deep: ITreeComponent[INode[A]],
    suffix: Option[IDigit[A]]
): ITreeComponent[A] =
  suffix match
    case None =>
      deep.viewRight match
        case Some(_ @ViewRightCons[A](newSuffix, newDeep)) =>
          Deep(prefix, newDeep, Digit1(newSuffix))
        case _ => prefix.toTreeComponent
    case Some(newSuffix) => Deep(prefix, deep, newSuffix)
```

Wenn das Suffix des deep größer als ein `Digit1` ist, kann aus das `Digit` ein `init` erzeugt werden, in dem das letzte Element weggelassen wird. Dann kann daraus einfach ein neuer FingerTree erzeugt werden, in dem nur das Suffix mit dem neuen Suffix ersetzt wird. Falls jedoch ein `Digit1` im Suffix gespeichert ist, wird eine Ebene weiter dieselbe Logik ausgeführt. Das bedeutet, dass dort dann das neue Suffix und das neue deep erzeugt werden. Falls jedoch in der darauffolgenden Ebene kein neues `ViewRightCons` erzeugt werden kann, zum Beispiel weil ein `Empty` darin gespeichert ist, dann wird das Prefix zu einer `TreeComponent` gemacht. Im Falle einer `Digit4` sieht das wie Folgt aus.

```scala
override def toTreeComponent: ITreeComponent[A] =
  Deep(Digit2(entry1, entry2), Empty(), Digit2(entry3, entry4))
```

Dabei wird aus den Einträgen ein `Deep` erzeugt, welches jeweils ein `Digit2` im Prefix und Suffix speichert.

Für den `tail` besteht dieselbe Logik, wie hinter `init`, jedoch mit spiegelverkehrter Logik, also einer `ViewLeft`.

### size

Um an die Größe des FingerTrees zu kommen, also der `size`, wird auf jedes gespeicherte Element die `size`-Funktion aufgerufen. Dabei wird in einem `Empty` wird als `size` 0 zurückgegeben. Bei einem `Single`, `Digit` oder `Node` werden die einzelnen Elemente darin auf ihre `size` abgefragt und anschließend summiert. Folgend ein Beispiel aus `Digit2`.

```scala
override def size: Int = measureSize(entry1) + measureSize(entry2)

private def measureSize(entry: A): Int =
  entry match
    case component: ITreeComponent[A] @unchecked => component.size
    case digit: IDigit[A] @unchecked             => digit.size
    case node: INode[A] @unchecked               => node.size
    case _                                       => 1
```

Für die Berechnung der `size` wird die private `measureSize`-Funktion aufgerufen, welche überprüft ob eine Komponente darin gespeichert ist. Falls ja, wird die `size` dieser genommen, falls nein ist die `size` des Elements 1.

In einem `Deep` wird nur die Summe aus dem Prefix, deep und Suffix gebildet.

```scala
override def size: Int = prefix.size + deep.size + suffix.size
```

### isEmpty

Die Logik der Abfrage, ob der FingerTree leer ist, ist simple gehalten. Ein `Empty` gibt in diesem Fall `true` zurück, jede andere Komponente ein `false`.

### toList

Das `toList` erzeugt eine Liste aller Elemente. Dabei gibt jede Komponente, ausgenommen das `Deep`, ihre Elemente als Lsite zurück. Folgend ein Beispiel aus `Node3`.

```scala
override def toList: List[A] = entry1 :: entry2 :: entry3 :: Nil
```

Im `Deep` wird dann, ähnlich wie bei der `size`, die Liste zusammengebaut. 

```scala
override def toList: List[A] = 
  prefix.toList ++: deep.toList.flatMap(a => a.toList) ++: suffix.toList ++: Nil
```

Dabei wird dann die Liste zusammengebaut, in dem die Liste des Prefix mit `++:`, was die Kurzschreibweise für `prependedAll` ist, an die Liste des gespeicherten deeps und daran dann das des Suffix angehängt wird. Da die Liste des deep auch über mehrere Ebenen gehen kann, wird mit `flatMap` dies aufgelöst.

### toString

Das `toString` ruft ebenfalls sich selbst auf die gespeicherten Elemente auf. 

```scala
override def toString: String =
  s"Deep( ${prefix.toString}, ${deep.toString}, ${suffix.toString} )"
```

Dabei wird der [String Interpolator](https://docs.scala-lang.org/overviews/core/string-interpolation.html) `s` benutzt, um den String übersichtlich und einfach zu erstellen. Mit `${}` können dann die Werte der Variablen eingefügt werden. 

## Quellen

[https://www.atlassian.com/blog/software-teams/covariance-and-contravariance-in-scala](https://www.atlassian.com/blog/software-teams/covariance-and-contravariance-in-scala)

[https://observablehq.com/@vpatryshev/finger-trees-in-js](https://observablehq.com/@vpatryshev/finger-trees-in-js)

[https://en.wikipedia.org/wiki/Finger_tree](https://en.wikipedia.org/wiki/Finger_tree)

[https://docs.scala-lang.org/scala3/book/introduction.html](https://docs.scala-lang.org/scala3/book/introduction.html)

[https://andrew.gibiansky.com/blog/haskell/finger-trees/](https://andrew.gibiansky.com/blog/haskell/finger-trees/)

[https://proglang.informatik.uni-freiburg.de/teaching/proseminar/2007ws/material/Nutz.pdf](https://proglang.informatik.uni-freiburg.de/teaching/proseminar/2007ws/material/Nutz.pdf)
