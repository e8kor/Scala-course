def selectInt: PartialFunction[Int, String] = {
  case 0 => "zero"
  case i if i < 0 => "negative"
  case i if i > 0 => "positive"
}

object ??! {

  def unapply(value: ??): Option[Int] = {
    value match {
      case l ?? r => Some(r.compare(l))
      case other => Some(0)
    }
  }
}

case class ??(l: String, r: String)


??("1", "2") match {
  case ??!(int) => int
}