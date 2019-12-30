package basics.context

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    println(coroutineContext)
}