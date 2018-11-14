package sample

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockHttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.io.ByteReadChannel
import kotlin.test.Test
import kotlin.test.assertEquals

class KtorClientTest {
    @Test
    fun mockWebCall() {
        runBlocking {
            val mockEngine = MockEngine {
                // Verify we called the correct url
                assertEquals("https://api.ipify.org/?format=json", url.toString())
                MockHttpResponse(
                    call = call,
                    status = HttpStatusCode.OK,
                    content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                    headers = Headers.build { set(HttpHeaders.ContentType, "application/json") }
                )
            }
            val ktorClient = KtorClient(mockEngine)
            val response = ktorClient.getIp()
            // Verify we serialized the response as expected
            assertEquals("127.0.0.1", response.ip)
        }
    }
}
