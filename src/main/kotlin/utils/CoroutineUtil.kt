package utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

fun debugPrintln(msg: String) {
    println("$msg in ${Thread.currentThread().name}")
}

fun debugPrintln() {
    println()
}

fun debugPrint(msg: String) {
    print("$msg in ${Thread.currentThread().name}")
}

fun CoroutineScope.debugPrintln(msg: String) {
    println("$msg in ${Thread.currentThread().name} $coroutineContext")
}

fun CoroutineScope.debugPrint(msg: String) {
    print("$msg in ${Thread.currentThread().name} $coroutineContext")
}

suspend fun debugContext() {
    println(coroutineContext)

    println("name=${coroutineContext[CoroutineName]}")
    println("interceptor=${coroutineContext[ContinuationInterceptor]}")
    println("job=${coroutineContext[Job]}")
    println("exceptionHandler=${coroutineContext[CoroutineExceptionHandler]}")
}