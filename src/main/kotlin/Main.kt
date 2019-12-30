import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking {
    println("Starting - ${Thread.currentThread().name}")

    val job1 = launch (Dispatchers.IO) {
        println("Launching1 - ${Thread.currentThread().name}")
        delay(1000)
        println("Returning1 - ${Thread.currentThread().name}")
    }

    val job2 = withContext(Dispatchers.IO) {
        println("Launching2 - ${Thread.currentThread().name}")
        delay(500)
        println("Returning2 - ${Thread.currentThread().name}")
    }

    println("Finishing ${Thread.currentThread().name}")
}