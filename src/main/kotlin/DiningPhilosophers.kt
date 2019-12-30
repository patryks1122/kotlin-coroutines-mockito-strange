import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.lang.IllegalStateException
import java.lang.RuntimeException
import kotlin.random.Random

suspend
fun main() = coroutineScope<Unit> {
    val fork1 = Fork("1")
    val fork2 = Fork("2")
    val fork3 = Fork("3")
    val fork4 = Fork("4")
    val fork5 = Fork("5")

    val philosophers = listOf(
        DiningPhilosopher("Aristotle", fork1, fork2).launchIn(this),
        DiningPhilosopher("Buddha", fork2, fork3).launchIn(this),
        DiningPhilosopher("Confucius", fork3, fork4).launchIn(this),
        DiningPhilosopher("Diogenes", fork4, fork5).launchIn(this),
        DiningPhilosopher("Erasmus", fork5, fork1).launchIn(this)
    )

    report(listOf(fork1, fork2, fork3, fork4, fork5), philosophers)
}

class Fork(val name : String) {
    private val mutex = Mutex()
    private var isUsed = false
    suspend fun use(who: String, function: suspend () -> Unit) {
        mutex.withLock {
            if (isUsed)
                throw IllegalStateException("Picking up used fork $name")
            isUsed = true
            println("Fork $name is picked up by $who")
            function()
            println("Fork $name is released by $who")
            isUsed = false
        }
    }

    override fun toString(): String {
        return "${if(isUsed) "F" else "f"}$name"
    }
}

class DiningPhilosopher(val name: String, private val fork1: Fork, private val fork2: Fork) {
    var holdingFork1 = false
    var holdingFork2 = false

    fun launchIn(scope: CoroutineScope): DiningPhilosopher {
        scope.launch {
            while (isActive) {
                think()
                fork1.use(name) {
                    holdingFork1 = true
                    think()
                    fork2.use(name) {
                        holdingFork2 = true
                        val eatingTime = Random.nextLong(1_000)
                        println("$name is eating for $eatingTime")
                        delay(eatingTime)
                        println("$name is not hungry anymore!")
                    }
                    holdingFork2 = false
                }
                holdingFork1 = false
            }
        }
        return this
    }

    private val state : String
    get() = when(holdingFork1 to holdingFork2) {
            false to false -> "not hungry"
            true to false -> "getting hungry"
            true to true -> "eating"
            else -> throw RuntimeException("in bad shape $holdingFork1:$holdingFork2")
        }

    override fun toString(): String {
        return "$name (${fork1} ${fork2}) is $state"
    }

    private suspend fun think() {
        val thinkingTime = Random.nextLong(1_000)
        println("${toString()} thinks for $thinkingTime")
        delay(thinkingTime)
        println("${toString()} has an idea!")
    }
}

suspend fun CoroutineScope.report(forks: List<Fork>, philosophers: List<DiningPhilosopher>) {
    while (isActive) {
        println()
        println(forks.joinToString(separator = "") { fork -> fork.toString()})
        println(philosophers.joinToString(separator = System.lineSeparator()) { philosopher -> philosopher.toString()  })
        println()
        delay(1_000)
    }
}
