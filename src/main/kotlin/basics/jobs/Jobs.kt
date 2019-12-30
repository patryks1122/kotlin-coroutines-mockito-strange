package basics.jobs

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import utils.debugPrintln

suspend
fun main()  {
    val job1 = GlobalScope.launch {
        debugPrintln("1st job")
        delay(2_000)
        debugPrintln("1st job done")
    }

    val job2 = GlobalScope.launch {
        debugPrintln("2nd job")
        delay(1_000)
        debugPrintln("2nd job done")
    }

    val job3 = GlobalScope.launch(start = CoroutineStart.LAZY) {
        debugPrintln("3rd job")
        delay(500)
        debugPrintln("3rd job done")
    }

//    delay(1_500)
//    debugPrintln("Joining ")
//    job1.join()
//    job2.cancel()
//    job1.join()
//    job3.join()
}

