package org.scalatrain.basic.four

/**
 * Created by evgeniikorniichuk on 25/06/15.
 */

import scalaz.Reader

case class User(firstName: String, lastName: String, supervisorId: Int, email: String)

trait UserRepository {
  def get(id: Int): User

  def find(username: String): User
}

trait UsersReader {

  import scalaz.Reader

  def getUser(id: Int) = Reader((userRepository: UserRepository) =>
    userRepository.get(id)
  )

  def findUser(username: String) = Reader((userRepository: UserRepository) =>
    userRepository.find(username)
  )
}

object ScalazReaderDITutorial extends App {

  val triple = Reader((i: Int) => i * 3)

  triple(3)
  // => 9

  val thricePlus2 = triple map (i => i + 2)

  thricePlus2(3)
  // => 11

  val f1 = for (i <- thricePlus2) yield i.toString

  f1(3)
  // => "11"

  val f2 = thricePlus2 map (i => i.toString)

  f2(3)


}
