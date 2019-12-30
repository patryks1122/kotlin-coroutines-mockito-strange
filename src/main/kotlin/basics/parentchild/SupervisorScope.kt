package basics.parentchild

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import utils.debugPrintln

suspend
fun main() {

    supervisorScope {
        launch {
            launch {
                fail(1_000, "1LLF") // this will fail a scope upwards
            }

            success(5_000, "LS") // this will be cancelled
        }
        launch {
            launch {
                success(50, "LLS") // will complete
            }
            success(2, "LS") // sure it completes
            success(500, "LS") // this completes
        }
        launch {
            success(10, "LS") // this completes
            success(20, "LS") // this completes
            launch {
                success(30, "LLS") // this completes
                success(40, "LLS") // this completes
                success(999, "LLS") // this probably not finished 10+20+30+40+999 = 1099
            }
            success(1_200, "LS") // no way
        }
    }

    coroutineScope {
        success(1) // boom
    }

}