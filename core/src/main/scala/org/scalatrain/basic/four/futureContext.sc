import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

val f1 = Future(1 + 1) map (_ + 1) flatMap (int => Future(int + 1)) andThen {
  case Success(1) => "success"
  case Failure(ex) => "failure"
}

val f = Future(5)

f andThen {
  case r => sys.error("runtime exception")
} andThen {
  case Failure(t) => println(t)
  case Success(v) => println(v)
}

val f3 = Future {
  Int.MaxValue
}

Future(6 / 0) recoverWith { case e: ArithmeticException => f3 }