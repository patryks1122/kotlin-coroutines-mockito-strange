package basics.jobs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val scope2 = CoroutineScope(SupervisorJob())

suspend
fun main() = runBlocking  {
    val job1 = launch {
        FiveTimesPrinter("Hello", 500).run()
    }

    val job2 = launch {
        FiveTimesPrinter("Moi", 1_000).run()
    }

//    delay(1_000)
//    job1.cancel()

}
/*scope2.launch*/
    //.join()

class FiveTimesPrinter(val message : String, val delay: Long) {
    fun run() {
        var counter = 0
        val start = System.currentTimeMillis()
        var nextTime = start

        while(counter < 5) {
            if (System.currentTimeMillis() >= nextTime) {
                println("$message ${counter++} will delay $delay")
                nextTime+=delay
            }
        }
    }
}