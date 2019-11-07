package test;

import LoginService
import NetworkUtil
import UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.withSettings
import org.mockito.Mockito.verify
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
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(networkUtil, loginService)
    }

    @Test
    fun testMocks() {

        assertTrue(MockUtil.isMock(networkUtil))
        assertTrue(MockUtil.isMock(loginService))
        assertFalse(MockUtil.isMock(userRepository))

    }

    @Test
    fun testLogin1(): Unit = runBlocking {

        // given
        val exception = IllegalArgumentException()
        `when`(networkUtil.getLoginToken("user", "pass")).thenReturn("user:pass")
        `when`(loginService.loginAsync("user:pass") ).thenThrow(exception)

        // when
        userRepository.loginUser1("user", "pass")

        // thez
        verify(networkUtil).getLoginToken("user", "pass")
        verify(networkUtil).exception(exception)
        Unit
    }

    @Test
    fun testLogin2(): Unit = runBlocking {

        // given
        val exception = IllegalArgumentException()
        `when`(networkUtil.getLoginToken("user", "pass")).thenReturn("user:pass")
        `when`(loginService.loginAsync("user:pass")).thenThrow(exception)

        // when
        userRepository.loginUser2("user", "pass")

        // then
        verify(networkUtil).getLoginToken("user", "pass")
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
            override fun exception(ex: Exception) : String {
                exception = true
                return super.exception(ex)
            }
        } , loginService)

        // given
        val fake = IllegalArgumentException()
        `when`(loginService.loginAsync("user:pass")).thenThrow(fake)

        // when
        userRepository.loginUser2("user", "pass")

        // then
        assertTrue(exception)
        Unit

    }

    @Test
    fun testLogin1_withStub(): Unit = runBlocking {

        var success = false
        var exception = false
        userRepository = UserRepository(object : NetworkUtil() {
            override fun success(response: Response<String>) : String {
                success = true
                return super.success(response)
            }
            override fun exception(ex: Exception) : String {
                exception = true
                return super.exception(ex)
            }
        } , loginService)

        // given
        val fake = IllegalArgumentException()
        `when`(loginService.loginAsync("user:pass")).thenThrow(fake)

        // when
        userRepository.loginUser1("user", "pass")

        // then
        assertTrue(exception)
        Unit

    }
}