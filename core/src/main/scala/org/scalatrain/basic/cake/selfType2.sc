trait CrudEntity_2[E] {
  def create(entityData: String): E

  def read(id: String): Option[E]

  def update(f: E => E): E

  def delete(id: String): Unit
}

case class Apple(name: String, age: Int) extends CrudEntity_2[Apple]

case class Bird(name: String, hobby: String) extends CrudEntity_2[Bird]

case class Orange(name: String, bankAccount: Double) extends CrudEntity_2[FloobyDust]

