package sample

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockHttpResponse
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.HttpRequest
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.io.ByteReadChannel
import kotlin.test.Test
import kotlin.test.assertEquals

class KtorClientTest {
    @Test fun mockWebCall() {
        runBlocking {
            val httpClient = HttpClient(MockEngine) {
                install(JsonFeature)
                engine {
                    check = { response: HttpRequest, call: HttpClientCall ->
                        // Verify we called the correct url
                        assertEquals("https://api.ipify.org/?format=json", response.url.toString())
                        MockHttpResponse(
                            call = call,
                            status = HttpStatusCode.OK,
                            content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                            headers = Headers.build { set(HttpHeaders.ContentType, "application/json") }
                        )
                    }
                }
            }
            val ktorClient = KtorClient(httpClient)
            val response = ktorClient.getIp()
            // Verify we serialized the response as expected
            assertEquals("127.0.0.1", response.ip)
        }
    }
}
