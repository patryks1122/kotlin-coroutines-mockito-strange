package basics.builders

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import utils.debugPrintln
import kotlin.system.measureTimeMillis


fun main() = runBlocking{
    val question = theQuestionAsync()
    val answer = theAnswerAsync()

    val totalTime = measureTimeMillis {
        debugPrintln("Answer to the ${question.await()} is ${answer.await()}")
    }

    debugPrintln("Found in $totalTime")
}

private fun theQuestionAsync(): Deferred<String> = GlobalScope.async<String> {
    val questionTime = measureTimeMillis {
        debugPrintln("Finding the question")
        delay(500)
    }
    debugPrintln("Found the question in $questionTime")
    "Ultimate Question of Life, the Universe, and Everything"
}

private fun theAnswerAsync(): Deferred<Int> = GlobalScope.async<Int> {
    val answerTime = measureTimeMillis {
        debugPrintln("Finding the answer")
        delay(1_000)
    }
    debugPrintln("Found the answer in $answerTime")
    42
}