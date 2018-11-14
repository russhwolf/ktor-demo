package sample

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.ios.IosClientEngine

actual val httpClientEngine: HttpClientEngine = IosClientEngine(HttpClientEngineConfig())
