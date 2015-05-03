// Seq: List, Vector, Range
val ls = List(1, 2, 3)

val ls1 = 1 :: 2 :: 3 :: Nil

ls match {
  case 1 :: 2 :: 3 :: Nil => true
  case other => false
}

ls equals ls1

val words = List("one", "two", "three")

words.toIndexedSeq.sortWith {
  case (a, b) =>
    a.size > b.size
}

words.toIndexedSeq.sortWith(_.length > _.length)

val map = (ls zip words).toMap

for ((w, idx) <- words.zipWithIndex) println(w, idx)

val ls2 = ls ::: ls1

ls match {
  case Nil =>
  case List(1, z@_*) => z
}

def foo(args: Int*) = println(args)

foo(ls: _*)

Vector(1, 2, 3)

Range(1, 5)
1 to 5
1 until 5

for (i <- 1 until ls.size) println(ls(i))

// Set: HashSet
Map.empty[Int, String]

// Map: HashMap, TreeMap, TrieMap