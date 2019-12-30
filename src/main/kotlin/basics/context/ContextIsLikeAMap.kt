package basics.context

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import utils.debugContext
import utils.debugPrintln
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


fun main() {
    debugPrintln("No coroutine no context")

    val empty: CoroutineContext = EmptyCoroutineContext
    val jobContext : CoroutineContext = Job()
    val nameContext : CoroutineContext = CoroutineName("korutyna")
    val dispatcher : CoroutineContext = Dispatchers.Unconfined
    val handler : CoroutineContext = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Got exception $throwable in $coroutineContext")
    }

    debugPrintln()

    debugPrintln("Starting in a new context")

    runBlocking(jobContext + nameContext + dispatcher + handler) {
        debugPrintln("In a new context")
        debugContext()
    }
}

