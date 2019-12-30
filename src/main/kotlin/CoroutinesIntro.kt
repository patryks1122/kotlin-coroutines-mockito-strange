import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import utils.debugPrintln
import java.util.concurrent.atomic.AtomicInteger
import kotlin.Long.Companion.MAX_VALUE
import kotlin.concurrent.thread

fun main() {
    val started = AtomicInteger(0)
//    launchThreads(started)
//    launchBlockingCoroutines(started)
    launchNonBlockingCoroutines(started)
}

fun launchNonBlockingCoroutines(started: AtomicInteger) {
    while (true) {
        val coroutineNumber = started.incrementAndGet()
        debugPrintln("Starting $coroutineNumber")
        GlobalScope.launch {
            debugPrintln("Started $coroutineNumber")
            delay(MAX_VALUE)
        }
    }
}

private fun launchBlockingCoroutines(
    started: AtomicInteger
) {
    while (true) {
        val coroutineNumber = started.incrementAndGet()
        debugPrintln("Starting $coroutineNumber")
        GlobalScope.launch {
            debugPrintln("Started $coroutineNumber")
            Thread.sleep(MAX_VALUE)
        }
    }
}

private fun launchThreads(
    started: AtomicInteger
) {
    while (true) {
        val threadNumber = started.incrementAndGet()
        debugPrintln("Starting $threadNumber")
        thread {
            debugPrintln("Running $threadNumber")
            Thread.sleep(MAX_VALUE)
        }
    }
}