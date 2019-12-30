package basics.builders

import kotlinx.coroutines.*
import utils.debugPrintln

//suspend
fun main() = runBlocking {
    val job = globalScopeLaunch()
//    job.cancel()
//    delay(1_500)
//    job.cancel()
//    job.join()
//    delay(1)
//    debugPrintln("Joining")
//    job.cancel()
    debugPrintln("Main done")
}
//    val job =
//    delay(500)
//    job.join()

fun globalScopeLaunch() = GlobalScope.launch {
    debugPrintln("Delaying 1_000...")
    delay(1_000)
    debugPrintln("Delaying 4_000...")
    delay(4_000)
    debugPrintln("... 5_000 done")
}