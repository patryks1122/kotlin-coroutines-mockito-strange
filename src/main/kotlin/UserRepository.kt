import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRepository(
    val networkUtil: NetworkUtil,
    val loginService: LoginService
) {
    suspend fun loginUser1(login: String, password: String) : String {
        val loginToken = networkUtil.getLoginToken(login, password)
        return try {
            val response = withContext(Dispatchers.IO) {
                loginService.loginAsync(loginToken)
            }
            networkUtil.success(response)
        } catch (ex: Exception) {
            networkUtil.exception(ex)
        }
    }

    suspend fun loginUser2(login: String, password: String) : String {
        val loginToken = networkUtil.getLoginToken(login, password)

        return try {
            val response = loginService.loginAsync(loginToken)
            networkUtil.success(response)
        } catch (ex: Exception) {
            networkUtil.exception(ex)
        }
    }
}