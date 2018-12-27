package sample

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.IosClientEngine
import io.ktor.client.engine.ios.IosClientEngineConfig

actual val httpClientEngine: HttpClientEngine = IosClientEngine(IosClientEngineConfig())
