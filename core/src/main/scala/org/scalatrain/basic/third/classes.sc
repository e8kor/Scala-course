import scala.beans.BeanProperty

abstract class AbstractDbService(val dbUrl: String, private var retryCount: Int = 0) {

  println(s"I'm ${getClass} constructor")

  def this() = {
    this("default", 1)
    println("Auxiliary constructor")
  }

  lazy val cache = {
    //calculate()
  }

  protected def doConnect(): Unit

  private[basic] val b = 2
  private val a = b + 1
  println(s"a = $a, b = $b, $name")
  val name = "Martin"
}

val anon = new AbstractDbService("") {
  override protected def doConnect(): Unit = println(s"Connect $b")
}

class OracleDbService extends AbstractDbService("oracle") {

  private val oracle = 1

  private[this] val thisOracle = 2

  def privacyTest(service: OracleDbService) = {
    service.oracle + thisOracle
    //        service.thisOracle
  }

  override protected def doConnect(): Unit = ???
}

val oracle = new OracleDbService

class User {
  private var _name: String = _

  @BeanProperty def name: String = _name

  def name_=(n: String) = {
    if (n.nonEmpty) _name = n
  }

  @BeanProperty var age: Int = _

  object gender

}

val u = new User
println(s"${u.name} ${u.age}")
u.name = "Alex"
u.name = ""
u.setAge(30)
println(s"${u.name} ${u.age} ${u.gender}")