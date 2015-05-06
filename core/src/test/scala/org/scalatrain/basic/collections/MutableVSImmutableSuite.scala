package org.scalatrain.basic.collections

import org.scalatrain.basic.collections.spec.MutableVSImmutableSpec

import scala.collection.mutable

/**
 * Created by evgeniikorniichuk on 05/05/15.
 */
class MutableVSImmutableSuite extends MutableVSImmutableSpec {

  behavior of "Mutable collections"

  it should " insert and += method to modify itself" in {
    val list = mutable.Buffer(1, 2, 3, 4, 5)

    assert((list += 1) === list)
    assert((1 +=: list) === list)

    (list insert(6, 6)) === list
  }

  it should "remove/update elements from collection" in {
    val list1 = mutable.Buffer(1, 2, 3, 4, 5)
    list1 update(2, -2)
    assert(list1(2) === list1(2))

    val list2 = mutable.Buffer(1, 2, 3, 4, 5)
    list2(2) = -2
    assert(list2(2) === list2(2)) // shortcut for update

  }

  behavior of "Immutable collections"

  it should "not be able to modify itself only produce new with updates" in {
    val list1 = List(1, 2, 3, 4, 5)
    assert((list1 :+ 1) !== list1)

    val list2 = mutable.Buffer(1, 2, 3, 4, 5)
    assert((1 +=: list2) === list2)
  }

}
