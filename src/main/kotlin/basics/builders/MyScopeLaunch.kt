package basics.builders

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.debugPrintln
import kotlin.coroutines.coroutineContext

/* = coroutineScope<Unit> */
/*  runBlocking<Unit> */
suspend
fun main()= coroutineScope<Unit>  {
    launch {
        debugPrintln("Delaying 5_000 ....")
        delay(5_000)
        debugPrintln("... 5_000 done")
    }

    launch {
        debugPrintln("Delaying 4_000 ....")
        delay(4_000)
        debugPrintln("... 4_000 done")
    }
}