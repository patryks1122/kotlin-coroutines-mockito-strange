package basics.jobs

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import utils.debugContext
import utils.debugPrintln
import java.lang.System.err

val supervisorJob = SupervisorJob()

val ceh = CoroutineExceptionHandler { _, throwable ->
    err.println("Caught $throwable at ${throwable.stackTrace.first()}")
}

val scope = CoroutineScope(ceh)

const val THROW = true

suspend
fun main() = scope.launch {
    val job1 = launch {
        debugPrintln("1st job starting")
        delay(1_000)
        debugPrintln("1st done")
    }

    val job2 = launch(/*Job()*/) {
        debugPrintln("2nd job starting")
        if (THROW) throw RuntimeException("Refusing to work!")
        debugPrintln("2nd done")
    }

    val job3 = launch {
        debugPrintln("3rd job starting")
        delay(100)
        debugPrintln("3rd done")
    }
}.join()