import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException


val ceh = CoroutineExceptionHandler { _, throwable -> println("Caught $throwable") }

fun main() {
    val job = SupervisorJob()

    runBlocking<Unit> {
        println("Inside runBlocking ${Thread.currentThread().name}")
        val jobLaunch1 = launch(Dispatchers.IO + ceh) {
            println("Inside launch ${Thread.currentThread().name}")
            throw RuntimeException()
        }

//        println(jobLaunch1 == job)
//        println(jobLaunch1 == job.children.first())

        launch {
            println("Inside launch2 ${Thread.currentThread().name}")
            delay(10)
            println("Finishing launch2 ${Thread.currentThread().name}")
        }

        delay(1)
    }
}