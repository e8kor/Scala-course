val a: Byte = 1
val b: Short = 2
val c: Int = 0x3
val d: Long = 4
val e: Char = '\u0041'
val f: Float = 0.5f
val g: Double = .6e8d
val h: Boolean = true || false
val symbol: Symbol = 'symbol
val i: Array[String] = new Array[String](5)


val j: String = s"a = $a, b = ${b.toString} \n"
val k: String = """Multi
        line \n"""

val l: String =
  """|10 LET A = 10
    |20 LET B = 20
    |30 PRINT A+B""".stripMargin


println(j)
println(k)
println(l)


val m: Symbol = 'OK // Erlang's Atom
val n: Symbol = 'WTF
val o = Symbol("OK")
println(s"m == n ${m == n}, m == o ${m == o}")

val p: Tuple3[Int, String, Boolean] = (1, "one", true)

val q: Function1[String, Boolean] = (s: String) => s.isEmpty()

val x: Unit = ()
val y: Null = null
lazy val z: Nothing = throw new RuntimeException("It's never gonna happen.")