package sample

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol

class KtorClient(private val httpClient: HttpClient) {
    suspend fun getIp(): IpResponse =
        httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.ipify.org"
            }
            parameter("format", "json")
        }
}

data class IpResponse(val ip: String)
