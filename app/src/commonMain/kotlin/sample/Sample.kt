package sample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun webcall() = GlobalScope.launch(ApplicationDispatcher) {
    val ip = KtorClient(httpClientEngine).getIp().ip
    println("ip = $ip")
}
