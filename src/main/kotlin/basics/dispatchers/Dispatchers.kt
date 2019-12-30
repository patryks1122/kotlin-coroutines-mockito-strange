package basics.dispatchers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

val singleThreadContext = newSingleThreadContext("epassi")
val threadPoolContext = newFixedThreadPoolContext(2, "pool")

val scope = CoroutineScope(SupervisorJob())

// scope.launch {
suspend
fun main() = runBlocking<Unit> {
    println("starting in ${Thread.currentThread().name} ")

    launch(Dispatchers.IO) {
        println("launch(Dispatchers.IO) runs in ${Thread.currentThread().name} ")
        yield()
        println("launch(Dispatchers.IO) resumes in ${Thread.currentThread().name} ")
    }

    launch(Dispatchers.Default) {
        println("launch(Dispatchers.Default) runs in ${Thread.currentThread().name} ")
        yield()
        println("launch(Dispatchers.Default) resumes in ${Thread.currentThread().name} ")
    }

    launch(Dispatchers.Unconfined) {
        println("launch(Dispatchers.Unconfined) runs in ${Thread.currentThread().name} ")
        yield()
        println("launch(Dispatchers.Unconfined) resumes in ${Thread.currentThread().name} ")
    }

    launch(Dispatchers.Main) {
        println("launch(Dispatchers.Main) runs in ${Thread.currentThread().name} ")
        yield()
        println("launch(Dispatchers.Main) resumes in ${Thread.currentThread().name} ")
    }

    launch(singleThreadContext) {
        println("launch(singleThreadContext) runs in ${Thread.currentThread().name} ")
        yield()
        println("launch(singleThreadContext) resumes in ${Thread.currentThread().name} ")
    }

    launch(threadPoolContext) {
        println("launch(threadPoolContext) runs in ${Thread.currentThread().name} ")
        yield()
        println("launch(threadPoolContext) resumes in ${Thread.currentThread().name} ")
    }

    launch(coroutineContext) {
        println("launch(_) runs in ${Thread.currentThread().name} ")
        yield()
        println("launch(_) resumes in ${Thread.currentThread().name} ")
    }



    println("finishing in ${Thread.currentThread().name} ")
}
//    .join()

/*
async(Dispatchers.IO) {
println("async(Dispatchers.IO) runs in ${Thread.currentThread().name} ")
yield()
println("async(Dispatchers.IO) resumes in ${Thread.currentThread().name} ")
}

async(Dispatchers.Default) {
println("async(Dispatchers.Default) runs in ${Thread.currentThread().name} ")
yield()
println("async(Dispatchers.Default) resumes in ${Thread.currentThread().name} ")
}

async(Dispatchers.Unconfined) {
println("async(Dispatchers.Unconfined) runs in ${Thread.currentThread().name} ")
yield()
println("async(Dispatchers.Unconfined) resumes in ${Thread.currentThread().name} ")
}

async(Dispatchers.Main) {
println("async(Dispatchers.Main) runs in ${Thread.currentThread().name} ")
yield()
println("async(Dispatchers.Main) resumes in ${Thread.currentThread().name} ")
}

async(singleThreadContext) {
println("singleThreadContext runs in ${Thread.currentThread().name} ")
yield()
println("singleThreadContext resumes in ${Thread.currentThread().name} ")
}

async(threadPoolContext) {
println("async(threadPoolContext) runs in ${Thread.currentThread().name} ")
yield()
println("async(threadPoolContext) resumes in ${Thread.currentThread().name} ")
}

async {
println("async(_) runs in ${Thread.currentThread().name} ")
yield()
println("async(_) resumes in ${Thread.currentThread().name} ")
}
        */


/*
withContext(Dispatchers.IO) {
        println("withContext(Dispatchers.IO) runs in ${Thread.currentThread().name} ")
        yield()
        println("withContext(Dispatchers.IO) resumes in ${Thread.currentThread().name} ")
    }

    withContext(Dispatchers.Default) {
        println("withContext(Dispatchers.Default) runs in ${Thread.currentThread().name} ")
        yield()
        println("withContext(Dispatchers.Default) resumes in ${Thread.currentThread().name} ")
    }

    withContext(Dispatchers.Unconfined) {
        println("withContext(Dispatchers.Unconfined) runs in ${Thread.currentThread().name} ")
        yield()
        println("withContext(Dispatchers.Unconfined) resumes in ${Thread.currentThread().name} ")
    }

    withContext(Dispatchers.Main) {
        println("withContext(Dispatchers.Main) runs in ${Thread.currentThread().name} ")
        yield()
        println("withContext(Dispatchers.Main) resumes in ${Thread.currentThread().name} ")
    }

    withContext(singleThreadContext) {
        println("withContext(singleThreadContext) runs in ${Thread.currentThread().name} ")
        yield()
        println("withContext(singleThreadContext) resumes in ${Thread.currentThread().name} ")
    }

    withContext(threadPoolContext) {
        println("withContext(threadPoolContext) runs in ${Thread.currentThread().name} ")
        yield()
        println("withContext(threadPoolContext) resumes in ${Thread.currentThread().name} ")
    }

    withContext(coroutineContext) {
        println("withContext(_) runs in ${Thread.currentThread().name} ")
        yield()
        println("withContext(_) resumes in ${Thread.currentThread().name} ")
    }
 */