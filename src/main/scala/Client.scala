import com.twitter.finagle._
import com.twitter.util.{Await, Future}

object Client extends App {
  val name: Name = Name.bound(Address("localhost", 10010), Address("localhost", 10011), Address("localhost", 10012))
  val client: Service[http.Request, http.Response] = Http.newService(name, "Services")
  val request = http.Request(http.Method.Get, "/")
  val response: Future[http.Response] = client(request)
  Await.result(response.onSuccess { rep: http.Response =>
    println("GET success: " + rep.getContentString())
  })
}
