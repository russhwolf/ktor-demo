package sample

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import kotlinx.serialization.Serializable

class KtorClient(httpClientEngine: HttpClientEngine) {
    private val httpClient = HttpClient(httpClientEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                setMapper(IpResponse::class, IpResponse.serializer())
            }
        }
    }
    suspend fun getIp(): IpResponse =
        httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.ipify.org"
            }
            parameter("format", "json")
        }
}

@Serializable
class IpResponse(val ip: String)
