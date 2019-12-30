import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*
import java.lang.RuntimeException
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime


suspend fun main() : Unit = coroutineScope {
    val millis = measureTimeMillis {
        launchBlocking()
        launchThrowing()
        launchNonBlocking()
    }

    coroutinePrint("Took $millis")
}

suspend fun main2()  {
    val millis = measureTimeMillis {

            val blockingJob = GlobalScope.launchBlocking()

            val throwingJob = GlobalScope.launchThrowing()

            val nonBlockingJob = GlobalScope.launchNonBlocking()

            delay(1_000)
            coroutinePrint("main done")

    }

    coroutinePrint(millis.toString())
}

private fun CoroutineScope.launchNonBlocking(): Job {
    return launch {
        coroutinePrint("starting nonblocking")
        nonBlockingLongJob(2_000)
        coroutinePrint("nonblocking done")
    }
}

private fun CoroutineScope.launchThrowing(): Job {
    return launch {
        coroutinePrint("starting throwing")
        throwingLongJob(5_000)
        coroutinePrint("throwing done")
    }
}

private fun CoroutineScope.launchBlocking(): Job {
    return launch {
        coroutinePrint("starting blocking")
        blockingLongJob(10_000)
        coroutinePrint("blocking done")
    }
}

suspend fun nonBlockingLongJob(sleep: Long = 5000) {
    delay(sleep)
}

fun blockingLongJob(sleep: Long = 2000) {
    Thread.sleep(sleep)
}

fun throwingLongJob(sleep: Long = 1000) {
    Thread.sleep(sleep)
    throw RuntimeException("Simulating fail")
}

fun coroutinePrint(msg: String) {
    println("${Thread.currentThread().name} $msg")
}

fun CoroutineScope.coroutinePrint(msg: String) {
    println("${Thread.currentThread().name} ${this.coroutineContext[CoroutineName.Key] ?: ""} $msg")
}
