import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http
import com.twitter.util.{Await, Future}

object Server extends App {
  val port = 10012
  val service = new Service[http.Request, http.Response] {
    def apply(req: http.Request): Future[http.Response] =
      Future.apply {
        println(s"Received request on $port")
        val response = http.Response(req.version, http.Status.Ok)
        response.setContentString(s"Response from $port")
        response
      }
  }
  val server = Http.serve(s":$port", service)
  Await.ready(server)
}
