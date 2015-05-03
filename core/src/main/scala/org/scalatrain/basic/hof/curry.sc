// def func(a: A, b: B): C convert to def curriedFunc(a: A): B => C
def foo(a: Int, b: String): Boolean = true
def curried(a: Int): String => Boolean = foo(a, _)

val func = curried(1)

foo(1, "1") == func("1") == curried(1)("1")

def filter(ints: Array[Int], p: Int => Boolean): Unit =
  for (i <- ints) if (p(i)) println(s"Filter $i")

def curriedFilter(ints: Array[Int]): (Int => Boolean) => Unit = filter(ints, _)

val array = Array(1, 2, 3)

val arrayEnclosed = curriedFilter(array)
arrayEnclosed(_ > 0)
def filter2(ints: Array[Int])(p: Int => Boolean): Unit = filter(ints, p)
def curriedFilter2(ints: Array[Int]): (Int => Boolean) => Unit = filter2(ints)

case class Sample1(count: Int, array: Array[Int])

case class Sample2(array: Array[Int])(count: Int = array length)

val v1 = Sample2(Array(1, 2, 3)) _
v1(3)

Sample2(Array(1, 2, 3))()