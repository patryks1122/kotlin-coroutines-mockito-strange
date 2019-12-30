package basics.exceptions

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.debugPrint
import utils.debugPrintln
import java.lang.Thread.sleep

suspend
fun main() = runBlocking {
    val job = launch {
        debugPrintln("Throwing")
        throw RuntimeException("Not working")
    }

    delay(1_000)
    debugPrintln("Exceptions from launch don't propagate")
}

/**
 * First we will use runBlocking
 */