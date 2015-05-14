package org.scalatrain.basic.collections

import org.scalatrain.basic.collections.spec.TraversableSpec

/**
 * Created by evgeniikorniichuk on 04/05/15.
 */
class TraversableSuite extends TraversableSpec {

  behavior of "Any collections type"

  it should "have default *.empty method" in {
    assert(Iterable() === (Iterable empty))
  }

  behavior of "Any empty collection"

  it should "produce NoSuchElementException when head/last and UnsupportedOperationException when tail/init was invoked" in {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
    intercept[NoSuchElementException] {
      Set.empty.last
    }
    intercept[UnsupportedOperationException] {
      Set.empty.tail
    }
    intercept[UnsupportedOperationException] {
      Set.empty.init
    }
  }

  behavior of "Any List-like collection"

  it should " *.size equals *.length (List like collections)" in {
    val list = List(1, 2, 3)
    assert((list size) === (list length))
  }


  it should "have reverse method, behaviour is obvious" in {
    val list = List(1, 2, 3)
    val reversed = list reverse

    //assert(list sameElements reversed) and its weird because of java == :-)
    assert(list !== reversed)
    assert(list === (reversed reverse))
  }

  behavior of "Any collection"

  it should "method foreach and its behaviour similar to java 'for'" in {
    val list = List(1, 2, 3)
    var count = 0

    list foreach {
      element =>
        count += 1
    }

    assert(count === 3)
    // reset counter
    count = 0

    for (element <- list) {
      count += 1
    }

    assert(count === 3)
  }

  it should "has  *.forall method which return true only if its true for all elements" in {

    val list = List(1, 2, 3)

    assert(list forall (element => element > 0))

    assert(list forall (_ > 0))

    val list2 = list :+ -1

    assert(!(list2 forall (_ > 0)))
  }

  it should
    """have drop method to create new collection without 'n' first elements with direction from left to right,
      | dropRight behaviour is opposite to 'drop' method, direction is from right to left
      | dropWhile to create new collections where predicate was true
      | and slice to create collection from given range,
      | similar behavior is for take/takeRight/takeWhile but instead
      | of dropping elements it create new collections with them""".stripMargin in {
    val list = List(1, 2, 3, 4, 5, 6, 7)

    assert(list drop 6 equals List(7))
    assert(list dropRight 6 equals List(1))
    assert(list dropWhile (_ < 6) equals List(6, 7))
    assert(list slice(2, 5) equals List(3, 4, 5))
    assert(list take 2 equals List(1, 2))
    assert(list takeRight 2 equals List(6, 7))
    assert(list takeWhile (_ < 3) equals List(1, 2))
  }

  it should
    """filter and filterNot which return new collection
      |but in first case is elements that true for predicate
      |and in second case elements that false for predicate""".stripMargin in {
    val list = Set(1, 1, 2, 2)

    assert(list filter (_ == 1) forall (_ == 1))
    assert(list filterNot (_ == 1) forall (_ == 2))
    assert((list filterNot (_ == 1)) ++ (list filter (_ == 1)) === list)
  }

  it should "have sort with method" in {
    val list = List(9, 3, 2, 5, 8, 4, 7, 6, 1)

    assert((list sortWith (_ < _)) === List(1, 2, 3, 4, 5, 6, 7, 8, 9))
  }

  behavior of "Monadic transformation in collections"

  it should
    """have two methods 'map', 'flatMap' which is enough to have to implement own collection :)
      | 'map' method invoked for each element of collection, generic type of collection can be modified this way
      | 'flatMap' method also invoked similar way, but as result you can change type of collection
    """.stripMargin in {
    val list = List(1, 2, 3)
    assert((list map (_ toString) isInstanceOf)[List[String]])

    val fruits = Seq("apple", "banana", "orange")
    assert((fruits flatMap (_ toUpperCase) isInstanceOf)[List[Char]])

    (fruits flatMap (element => (element toUpperCase) toSet)) === List('A', 'P', 'L', 'E', 'B', 'A', 'N', 'E', 'N', 'A', 'G', 'R', 'O')
    (fruits flatMap (element => element toUpperCase)) === List('A', 'P', 'P', 'L', 'E', 'B', 'A', 'N', 'A', 'N', 'A', 'O', 'R', 'A', 'N', 'G', 'E')

    def g(v: Int) = List(v - 1, v, v + 1)

    assert((list map (x => g(x))) === List(List(0, 1, 2), List(1, 2, 3), List(2, 3, 4)))
    assert((list flatMap (x => g(x))) === List(0, 1, 2, 1, 2, 3, 2, 3, 4))
  }

  it should
    """have methods fold/foldLeft/foldRight and reduce/reduceLeft/reduceRight
      |difference between fold and reduce that you iterate from your own argument and first in case of fold/foldLeft/foldRight
      |and in case of reduce you iterating from first and second argument
    """.stripMargin in {

    // fold/foldLeft/foldRight
    val list = List(1, 2, 3)
    assert(list.fold(-3) {
      case (accum, element) =>
        accum + element
    } === 3)

    // directional fold
    assert(list.foldLeft(-3) {
      case (accum, element) =>
        accum + element
    } === 3)

    assert(list.foldRight(-3) {
      case (accum, element) =>
        accum + element
    } === 3)

    // reduce/reduceLeft/reduceRight

    assert(list.reduce(_ + _) === 6)

    assert(list.reduceLeft(_ + _) === 6)

    assert(list.reduceRight(_ + _) === 6)


    // good to know :-)

    assert(list.reduce(_ + _) === list.sum)

    assert(list.reduce(_ * _) === list.product)

    assert((list.reduce[Int] {
      case (l, r) if l > r => l
      case (l, r) => r
    }) === (list max))

    assert((list.reduce[Int] {
      case (l, r) if l < r => l
      case (l, r) => r
    }) === (list min))
  }

  it should "have splitAt and partition method to split collection by predicate or by value" in {
    val list = List(1, 2, 3)

    assert((list splitAt 1) === ((List(1), List(2, 3))))

    assert((list partition (_ < 3)) === ((List(1, 2), List(3))))
  }

  it should " have filter method to produce new collection of elements where predicate is 'true'" in {
    val list = List(1, 2, 3, 4)
    assert((list filter (_ % 2 == 0)) === List(2, 4))
  }
  it should
    """ have find and exists methods
      |find returns option on element for which predicate is true if no elements then it will be None
      |and exists just says you is such element exists
    """.stripMargin in {
    val list = List(1, 2, 3, 4)
    assert((list find (_ == 3)) === Some(3))
    assert((list exists (_ == 3)) === true)
  }

  it should
    """have a bunch of to(Set/List/Iterable/Iterator/Array etc) methods
      |that convert your collection to another type
      |of collection with same elements""".stripMargin in {
    val list = List(1, 2, 3)

    assert((list toSet) === Set(1, 2, 3))
    assert((list toSeq) === Seq(1, 2, 3))
  }

  it can " be zipped with other collections" in {
    val indexes = List(0, 1, 2, 3)
    val words = List("zero", "one", "two", "three")

    assert((words zip indexes) === (words zipWithIndex))

    val map = (indexes zip words) toMap

    assert(map(1) === "one")
  }
}
