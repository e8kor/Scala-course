package org.scalatrain.basic.curry

import org.scalatrain.basic.curry.spec.CurrySpec

/**
 * Created by evgeniikorniichuk on 16/05/15.
 */
class CurrySuite extends CurrySpec {

  behavior of "curried function"

  it can "be passed to as argument to functions " in {

    def nonCurriedF(i: Int, n: Int) = {
      i + n
    }

    val list = List(1, 2, 3, 4, 5)

    assert((list reduce nonCurriedF) === (list foldLeft 0)(nonCurriedF))

    def curriedF(i: Int)(n: Int) = {
      i + n
    }

    import Function._
    assert((list reduce uncurried(curriedF)) === (list foldLeft 0)(uncurried(curriedF)))

    val curr0 = curriedF(0)
    val curr1 = curriedF(1)
    val curr2 = curriedF(2)
    val curr3 = curriedF(3)
    val curr4 = curriedF(4)
    val curr5 = curriedF(5)

    val listCurr = curr1 andThen curr2 andThen curr3 andThen curr4 andThen curr5

    assert((list foldLeft 0)(uncurried(curriedF)) === listCurr(0))
    assert {
      true
    }
  }

  behavior of "ReaderMonad function"

}