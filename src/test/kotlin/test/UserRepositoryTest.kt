package test;

import LoginService
import NetworkUtil
import UserRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.withSettings
import kotlinx.coroutines.runBlocking
import org.apache.commons.lang3.mutable.MutableObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.refEq
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.internal.util.MockUtil
import retrofit2.Response

internal class UserRepositoryTest {
    @Mock
    lateinit var networkUtil: NetworkUtil

    @Mock
    lateinit var loginService: LoginService

    lateinit var userRepository: UserRepository

    @BeforeEach
    internal fun setUp() {
        networkUtil = mock(verboseLogging = true)
        loginService = mock(verboseLogging = true)
        userRepository = UserRepository(networkUtil, loginService)
    }

    @Test
    fun test() = runBlocking<Unit> {
        val exception = IllegalArgumentException()
        println(System.identityHashCode(exception))
        `when`(loginService.loginAsync("user:pass") ).thenThrow(exception)
    }

    @Test
    fun testMocks() {
        assertTrue(MockUtil.isMock(networkUtil))
        assertTrue(MockUtil.isMock(loginService))
        assertFalse(MockUtil.isMock(userRepository))
    }

    @Test
    fun testLogin1(): Unit = runBlocking<Unit> {
        // given
        val exception = IllegalArgumentException()
        `when`(loginService.loginAsync("user:pass") ).thenThrow(exception)

        // when
        userRepository.loginUser1("user", "pass")

        // then
        verify(networkUtil).exception(exception)
    }

    @Test
    fun testLogin2(): Unit = runBlocking {
        // given
        val exception = IllegalArgumentException()
        `when`(loginService.loginAsync("user:pass")).thenThrow(exception)

        // when
        userRepository.loginUser2("user", "pass")

        // then
        verify(networkUtil).exception(exception)
        Unit
    }

    @Test
    fun testLogin2_withStub(): Unit = runBlocking {
        var success = false
        var exception = false
        userRepository = UserRepository(object : NetworkUtil() {
            override fun success(response: Response<String>) : String {
                success = true
                return super.success(response)
            }
            override fun exception(ex: Exception?) : String {
                exception = true
                println(System.identityHashCode(ex))
                return super.exception(ex)
            }
        } , loginService)

        // given
        val fake = IllegalArgumentException()
        println(System.identityHashCode(fake))
        `when`(loginService.loginAsync("user:pass")).thenThrow(fake)

        // when
        userRepository.loginUser2("user", "pass")

        // then
        assertTrue(exception)
        assertFalse(success)
        Unit

    }

    @Test
    fun testLogin1_withStub(): Unit = runBlocking<Unit> {
        var success = false
        var exception = false
        val exceptionHolder = MutableObject<java.lang.Exception>()
        userRepository = UserRepository(object : NetworkUtil() {
            override fun success(response: Response<String>) : String {
                success = true
                return super.success(response)
            }
            override fun exception(ex: Exception?) : String {
                exception = true
                exceptionHolder.value = ex
                return super.exception(ex)
            }
        } , loginService)

        // given
        val fake = IllegalArgumentException()
        println(System.identityHashCode(fake))
        `when`(loginService.loginAsync("user:pass")).thenThrow(fake)

        // when
        userRepository.loginUser1("user", "pass")

        // then
        assertTrue(exception)
        assertFalse(success)
        assertSame(fake, exceptionHolder.value)
    }
}