// Binary operators

// Infix
1 + 2 == 1.+(2)
1 == 1 == 1.==(1)

// Postfix, not recommended
val one = 1 toString // newline is required here!
val i = one concat "0" charAt 0

println(i)

// Prefix unary operators: +, -, !, and ~
class U {
  def unary_! = println("!!!")

  // right associative operators end with ':'
  def +:(i: Int) = println(s"+: $i")
}
val u = new U
!u
u.unary_!

2 +: u // same as u.+:(2)