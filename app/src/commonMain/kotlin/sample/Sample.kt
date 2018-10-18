package sample

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

fun hello(): String = "Hello from ${Platform.name}"

class Proxy {
    fun proxyHello() = hello()
}

fun main(args: Array<String>) {
    println(hello())
}

fun webcall() = GlobalScope.launch(ApplicationDispatcher) {
    val ip = KtorClient(HttpClient { install(JsonFeature )}).getIp().ip
    println("ip = $ip")
}
