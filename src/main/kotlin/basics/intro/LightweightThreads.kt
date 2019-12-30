package basics.intro

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import utils.debugPrintln
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

val counter = AtomicInteger(0)

suspend fun main()
        = threads()
//= coroutineScope {coroutines()}
//= coroutineScope {
//    coroutinesWithBlockingCode()
//}

fun threads() {
    while (true) {
        val threadNumber = counter.incrementAndGet()
        debugPrintln("Launching $threadNumber thread" )
        thread {
            debugPrintln("Launched $threadNumber thread" )
            Thread.sleep(Long.MAX_VALUE)
        }
    }
}

fun CoroutineScope.coroutines() {
    while (true) {
        val coroutineNumber = counter.incrementAndGet()
        debugPrintln("Launching $coroutineNumber coroutine" )
        launch {
            debugPrintln("Launched $coroutineNumber coroutine" )
            delay(Long.MAX_VALUE)
        }
    }
}

fun CoroutineScope.coroutinesWithBlockingCode() {
    while (true) {
        val coroutineNumber = counter.incrementAndGet()
        debugPrintln("Launching $coroutineNumber coroutine" )
        Thread.sleep(500)
        launch {
            debugPrintln("Launched $coroutineNumber coroutine" )
            Thread.sleep(Long.MAX_VALUE)
        }
    }
}
