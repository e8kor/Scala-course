// Collections can be
val c = Array(1, 2, 3)
val c2 = c.map(_ * 2)
val c3 = c ++ c2
c3.mkString
//    val c4 = c3 collect { case i if i > 5 => 0 }
val c4 = c3.filter { i => i > 5 }.map { i => 0 }


c take (2)
c takeRight (2)
c dropRight (1)
c drop (1)


val fruits = Seq("apple", "banana", "orange")

fruits flatMap (elem => (elem toUpperCase) toSet)
fruits flatMap (elem => elem toUpperCase)

val list = List(1, 2, 3)
val reversed = list reverse

list sameElements reversed

list ne reversed
!(list equals reversed)
list equals (reversed reverse)

c4.mkString
c.length
c.isEmpty && c.nonEmpty
c.hasDefiniteSize
val result = c.find(_ > 2).map(_.toString)
val result2 = c.collectFirst { case i if i > 2 => i.toString }
result2
Array(1 -> "one", 2 -> "two").toList.apply(1)
c.takeWhile(_ < 5).mkString
c.drop(1).mkString
c.dropWhile(_ < 5).mkString
(c.head, c.tail.mkString)
(c.init.mkString, c.last)
c.slice(1, 2).mkString
c.count(_ > 2)
c.foldLeft(0) { case (acc, i) => acc + i }
(0 /: c) { case (acc, i) => acc + i }
c.reduceLeft(_ + _) == c.sum //1, 2 => 3, 3 => 6


val list2 = List(1, 2, 3, 4, 5)
def g(v: Int) = List(v - 1, v, v + 1)

list2.map(x => g(x))
list2.flatMap(x => g(x))


c.min + c.max + c.sum + c.product
c.mkString("start", ",", "end")
// Addition, ++
// map, flatMap, and collect
// Conversions toArray, toList, toIterable, toSeq, toIndexedSeq, toStream, toSet, toMap
// Size info operations isEmpty, nonEmpty, size, and hasDefiniteSize
// Element retrieval operations head, last, headOption, lastOption, and find
// Sub-collection retrieval operations tail, init, slice, take, drop, takeWhile, dropWhile, filter, filterNot, withFilter
// Element tests exists, forall, count
// Folds foldLeft, foldRight, /:, :\, reduceLeft, reduceRight
// Specific folds sum, product, min, max
// mkString