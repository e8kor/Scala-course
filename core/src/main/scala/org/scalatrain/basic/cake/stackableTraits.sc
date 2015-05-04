class Printer {
  def print(str: String): Unit = {
    println(str)
  }
}


//class PrettyPrinter extends Printer {
//  override def print(str: String): Unit = {
//    print("pretty ")
//    super.print(str)
//  }
//}

trait Pretty extends Printer {
  abstract override def print(str: String): Unit = {
    println("Pretty invoked")
    super.print("pretty " + str)
  }
}

trait NewLine extends Printer {
  abstract override def print(str: String): Unit = {
    println("NewLine invoked")
    super.print(str + " *new line!*")
  }
}

trait Caps extends Printer {
  abstract override def print(str: String): Unit = {
    println("Caps invoked")
    super.print(str.capitalize)
  }
}

val printer = new Printer with Pretty with NewLine with Caps

printer.print("hello")