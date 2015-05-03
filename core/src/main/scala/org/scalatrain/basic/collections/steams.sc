
// STREAM
val stream = Stream from 1

stream hasDefiniteSize

stream length // error as not defined

(stream isEmpty) && stream.nonEmpty


val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(
  fibs.tail).map(n => {
  println("Adding %d and %d".format(n._1, n._2))
  n._1 + n._2
})

fibs take 5 foreach println
fibs take 6 foreach println