package test

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import kotlin.coroutines.coroutineContext

internal class MockitoTest  {

    class Internal {
        fun normal() = ""

        suspend fun suspending() : String {
            delay(1000)
            return ""
        }
    }

    class SUT(private val internal: Internal) {
        fun blocking() : String = runBlocking {
            internal.suspending()
        }

        suspend fun suspending() : String
           = withContext(coroutineContext)
        {
            internal.suspending()
        }
    }


    @Test
    fun testInternal() {
        val internal = mock<Internal>()

        val exception = IllegalArgumentException()

        whenever(internal.normal()).thenThrow(exception)

        try {
            internal.normal()
        } catch (ex: Exception) {
            assertSame(exception, ex)
            println(ex.stackTrace.first())
            assertEquals(49, ex.stackTrace.last().lineNumber)
            return
        }
        fail("Should not reach here")
    }

    @Test
    fun testInternalSuspending() = runBlocking<Unit> {
        val internal = mock<Internal>(verboseLogging = false)

        val fake = IllegalArgumentException()

        whenever(internal.suspending()).thenThrow(fake)

        try {
            internal.suspending()
        } catch (ex: Exception) {
            assertSame(fake, ex)
            return@runBlocking
        }
        fail("Should not reach here")
    }

    @Test
    fun testBlocking() = runBlocking<Unit> {
        val internal = mock<Internal>()
        val sut = SUT(internal)

        val fake = IllegalArgumentException()

        whenever(internal.suspending()).thenThrow(fake)

        try {
            sut.blocking()
        } catch (ex: Exception) {
            assertSame(fake, ex)
            return@runBlocking
        }
        fail("Should not reach here")
    }

    @Test
    fun testSuspendingWithContextChange() = runBlocking<Unit> {
        val internal = mock<Internal>(verboseLogging = false)
        val sut = SUT(internal)

        val exception = IllegalArgumentException()

        whenever(internal.suspending()).thenThrow(exception)

        try {
            sut.suspending()
        } catch (ex: Exception) {
            assertEquals(exception, ex)
            return@runBlocking
        }
        fail("Should not reach here")
    }


    class MyException : RuntimeException() {
        init {
            println("Someones inits me " + Exception().stackTrace.joinToString(separator = ",\n "))
        }
    }
}