import scalaz.Reader

val triple = Reader((i: Int) => i * 3)

triple(3) // => 9

val thricePlus2 = triple map (i => i + 2)

thricePlus2(3) // => 11

val f1 = for (i <- thricePlus2) yield i.toString

f1(3) // => "11"

val f2 = thricePlus2 map (i => i.toString)

f2(3)

trait UsersReader {

  import scalaz.Reader

  def getUser(id: Int) = Reader((userRepository: UserRepository) =>
    userRepository.get(id)
  )

  def findUser(username: String) = Reader((userRepository: UserRepository) =>
    userRepository.find(username)
  )
}