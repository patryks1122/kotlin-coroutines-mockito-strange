package basics.context

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.debugPrintln
import kotlin.coroutines.ContinuationInterceptor


/** probably skip */
fun main() = runBlocking<Unit>{
    val dispatcher = coroutineContext[ContinuationInterceptor.Key]


    val defaultJob = launch(Dispatchers.Default) {
        debugPrintln("-> Dispatchers.Default")
        delay(500)
        debugPrintln("<- Dispatchers.Default")
    }

//    debugPrintln("after Dispatchers.Default${System.lineSeparator()}")

    val ioJob = launch(Dispatchers.IO) {
        debugPrintln("-> Dispatchers.IO")
        delay(400)
        debugPrintln("<- Dispatchers.IO")
    }

//    debugPrintln("after Dispatchers.IO${System.lineSeparator()}")

    val unconfinedJob = launch(Dispatchers.Unconfined) {
        debugPrintln("-> Dispatchers.Unconfined")
        delay(300)
        debugPrintln("<- Dispatchers.Unconfined")
    }

//    debugPrintln("after Dispatchers.Unconfined${System.lineSeparator()}")

    val mainJob = launch(dispatcher!!) {
        debugPrintln("-> Dispatchers.Main")
        delay(200)
        debugPrintln("-> Dispatchers.Main")
    }

    joinAll(defaultJob, ioJob, unconfinedJob, mainJob)

//    debugPrintln("after Dispatchers.${coroutineContext[ContinuationInterceptor.Key]}")
}