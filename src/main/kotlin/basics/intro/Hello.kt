package basics.intro

import basics.hello
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    val s = runBlocking {
        hello("World")
    }

}

