package basics.parentchild

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import utils.debugPrintln

/**
 * Cancellation of parent with cancel or its exceptional completion (failure)
 *  immediately cancels all its children.
 *
 * Parent cannot complete until all its children are complete.
 *  Parent waits for all its children to complete in completing or cancelling state.
 *
 * Uncaught exception in a child, by default, cancels parent.
 *  In particular, this applies to children created with launch coroutine builder.
 *  Note that async and other future-like coroutine builders do not have uncaught exceptions by definition,
 *  since all their exceptions are caught and are encapsulated in their result.
 */
suspend
fun main() {
    coroutineScope {
        launch {
            launch {
                fail(1_000, "LLF") // this will fail a scope upwards
            }

            success(5_000, "LS") // this will be cancelled
        }
        launch {
            launch {
                success(50, "LLS") // will complete
            }
            success(2, "LS") // sure it completes
            success(500, "LS") // this completes
        }
        launch {
            success(10, "LS") // this completes
            success(20, "LS") // this completes
            launch {
                success(30, "LLS") // this completes
                success(40, "LLS") // this completes
                success(999, "LLS") // this probably not finished 10+20+30+40+999 = 1099
            }
            success(1_200, "LS") // no way
        }
    }

    coroutineScope {
        success(1) // boom
    }
}

suspend fun fail(time: Long, header: String = "") {
    delay(time)
    debugPrintln("$header Failed in $time")
    throw RuntimeException("Failed ")
}

suspend fun success(time: Long, header: String = "") : String {
    delay(time)
    debugPrintln("$header Succeeded in $time")
    return time.toString()
}
