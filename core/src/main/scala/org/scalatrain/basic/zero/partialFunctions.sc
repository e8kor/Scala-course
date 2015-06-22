def isDigit(s: String) = try {
  Integer.valueOf(s)
  true
} catch {
  case e: NumberFormatException => false
}

val pf = new PartialFunction[String, Int] {
  override def isDefinedAt(x: String): Boolean = isDigit(x)

  override def apply(v1: String): Int = Integer.valueOf(v1)
}

def applyOrDefault(s: String, default: Int) = {
  if (pf.isDefinedAt(s)) pf(s) else default
}

println(applyOrDefault("1 2 3", 0))

println(applyOrDefault("123", 0))

val pf2: PartialFunction[String, Int] = {
  case s: String if isDigit(s) => Integer.valueOf(s)
}

println(pf2.isDefinedAt("1 2 3"))
println(pf2("123"))