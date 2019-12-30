package basics.intro

import github.githubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val scope = CoroutineScope(Job())


suspend
fun main() = runBlocking<Unit> {
    launch {
        val organization = "Mobilization"
        val noRepos = githubService.getOrgRepos(organization).size
        println(noRepos)
    }

    launch {
        val organization = "jetbrains"
        val noRepos = githubService.getOrgRepos(organization).size
        println(noRepos)
    }
}



/*
 = runBlocking<Unit>
 githubService.getOrgRepos(organization).size
 */

/*
val topContributor = githubService.getOrgRepos(organization)
        .map { repo ->
            async { githubService.getRepoContributors(organization, repo.name) }
        }
        .awaitAll()
        .flatten()
        .groupBy {
            it.login
        }
        .map { userAndContributions ->
            userAndContributions.key to userAndContributions.value.sumBy { it.contributions }
        }
        .maxBy {
            it.second
        }

        println("$organization top contributor is ${topContributor?.first} with ${topContributor?.second} contributions")
 */