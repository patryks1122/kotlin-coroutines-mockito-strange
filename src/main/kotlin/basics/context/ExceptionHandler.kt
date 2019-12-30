package basics.context

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.coroutines.CoroutineContext

val handler = CoroutineExceptionHandler {
        coroutineContext: CoroutineContext, throwable: Throwable ->
    println("CoroutineExceptionHandler Caught $throwable in $coroutineContext")
}

val uncaughtExceptionHandler = Thread.UncaughtExceptionHandler {
        thread: Thread, throwable: Throwable ->
    println("Thread.UncaughtExceptionHandler Caught $throwable in $thread")

}


fun main() = runBlocking<Unit> {
    Thread.currentThread().uncaughtExceptionHandler = uncaughtExceptionHandler
    supervisorScope {
        launch(handler) {
            throw IllegalStateException("Refusing to work in supervisor scope!")
        }
    }

    launch(handler) {
        throw IllegalStateException("Refusing to work in launch scope!")
    }

}