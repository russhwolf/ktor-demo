package sample

import io.ktor.client.HttpClient

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

suspend fun webcall() {
    val ip = KtorClient(HttpClient()).getIp().ip
    println("ip = $ip")
}
