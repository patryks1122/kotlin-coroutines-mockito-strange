import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking {
    println("Starting - ${Thread.currentThread().name}")

    val job1 = GlobalScope.launch {
        println("Launching1 - ${Thread.currentThread().name}")
        delay(1000)
        println("Returning1 - ${Thread.currentThread().name}")
    }

    val job2 = GlobalScope.launch {
        println("Launching2 - ${Thread.currentThread().name}")
        delay(500)
        println("Returning2 - ${Thread.currentThread().name}")
    }

    println("Joining ${Thread.currentThread().name}")
    job1.join()
    job2.join()
    println("Joined ${Thread.currentThread().name}")

    println("Finishing ${Thread.currentThread().name}")
}