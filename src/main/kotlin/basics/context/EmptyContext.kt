package basics.context

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import utils.debugContext
import kotlin.coroutines.coroutineContext

suspend
fun main() {
    debugContext()

    println()

    GlobalScope.launch {
        debugContext()
    }.join()
}

