package basics.context

import github.githubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

/** skip this */
class AtomicMax {
    private val atomicInteger = AtomicInteger(0)
    private val atomicMax = AtomicInteger(0)

    fun increment() {
        val concurrent = atomicInteger.incrementAndGet()
            atomicMax.updateAndGet {
                current -> if (concurrent > current) concurrent else current
            }
    }

    fun decrement() {
        atomicInteger.decrementAndGet()
    }

    fun get() = atomicMax.get()
    override fun toString(): String = atomicMax.get().toString()
}

val atomicMax = AtomicMax()

fun main() {
    val organization = "jetbrains"

    runBlocking<Unit> {
        val contributors = withContext(Dispatchers.IO) {
            githubService.getOrgRepos(organization)
                .map { async {
                    try {
                        atomicMax.increment()
                        githubService.getRepoContributors(organization, it.name)
                    }
                    finally {
                        atomicMax.decrement()
                    }
                } }
                .awaitAll()
                .flatten()
                .asSequence()
                .groupBy { it.login }
                .map { login2Contributions ->
                    login2Contributions.key to login2Contributions.value.sumBy { it.contributions } }
                .sortedByDescending { it.second }
                .take(10)
                .toList()
        }

        println(contributors)
        println(atomicMax)
    }
}