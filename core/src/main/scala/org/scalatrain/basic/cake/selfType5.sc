trait CrudEntity[E <: CrudEntity[E]] {
  self: E =>
  def create(entityData: String): E

  def read(id: String): Option[E]

  def update(f: E => E): E

  def delete(id: String): Unit
}

case class Orange(name: String, age: Int) extends CrudEntity[Apple]