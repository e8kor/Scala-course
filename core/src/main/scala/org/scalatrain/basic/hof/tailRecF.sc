import scala.annotation.tailrec


@tailrec
def tail_calculate(x: Int, y: Int = 1): Int = {
  if (x == 1) y else tail_calculate(x - 1, x * y)
}

def head_calculate(x: Int, y: Int = 1): Int = {
  if (x == 1) y else head_calculate(x - 1, x * y)
}

println(tail_calculate(10, 10))
println(head_calculate(10, 10))
