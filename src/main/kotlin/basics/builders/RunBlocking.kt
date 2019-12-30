package basics.builders

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import utils.debugPrint
import utils.debugPrintln


//suspend
fun main() = runBlocking{
    debugPrintln("Delaying....")
    delay(1_000)
    debugPrintln("... done")
}