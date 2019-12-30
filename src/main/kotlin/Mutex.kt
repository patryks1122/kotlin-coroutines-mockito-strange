import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import utils.debugPrint

suspend
fun main() = coroutineScope<Unit> {

    val mutex = Mutex()

    mutex.withLock {
        println("In lock")
        delay(3_000)
        println("Leaving")
    }
}