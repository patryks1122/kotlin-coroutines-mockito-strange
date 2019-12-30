package basics.exceptions

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import utils.debugPrintln
import java.lang.Exception
import java.lang.RuntimeException

suspend
fun main() = coroutineScope<Unit> {
    try {
        launch {
            throw RuntimeException("Won't work")
        }
    } catch (e: Exception) {
        debugPrintln("Caught from launch")
    }

    val deferred = async {
        throw RuntimeException()
    }

    try {
        deferred.await()
    } catch (e: Exception) {
        debugPrintln("Caught from await")
    }
}