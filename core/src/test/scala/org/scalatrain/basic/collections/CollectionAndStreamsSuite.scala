package org.scalatrain.basic.collections

import org.scalatrain.basic.collections.spec.CollectionAndStreamsSpec

/**
 * Created by evgeniikorniichuk on 05/05/15.
 */
class CollectionAndStreamsSuite extends CollectionAndStreamsSpec {

  behavior of "Streams"

  it should
    """have non defined size instead of Seq,Lists or Sets. Never call length for Streams,
      |unless you know what you doing, with it you ask stream to process all elements and return you exact elements""".stripMargin in {

    val stream = Stream from 1

    val collection = Seq(1)

    assert((stream hasDefiniteSize) === false)

    assert((collection hasDefiniteSize) === true)
  }

  it should "support pattern matching " in {
    val stream = Stream iterate(1, 10)
    stream match {
      case head #:: tail => assert(head === 1)
      case _ => fail("should match on first case")
    }
  }

  it should "have lazy implementation for each calculation of each element" in {
    val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: (fibs zip (
      fibs tail) map {
      case (l, r) =>
        println("Adding %d and %d" format(l, r))
        l + r
    })

    assert(fibs(1) === 0) // no output
    assert(fibs(2) === 1) // still no output
    assert(fibs(3) === 2) // whoups! element added

  }


}
