import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRepository(
    val networkUtil: NetworkUtil,
    val loginService: LoginService
) {
    suspend fun loginUser1(login: String, password: String) : String {
        val loginToken = "$login:$password"
        return try {
            val response = withContext(Dispatchers.IO) {
                val response = loginService.loginAsync(loginToken)
                response
            }
            networkUtil.success(response)
        } catch (ex: Exception) {
            return networkUtil.exception(ex)
        }
    }

    suspend fun loginUser2(login: String, password: String) : String {
        val loginToken = "$login:$password"
        return try {
            val response = loginService.loginAsync(loginToken)
            networkUtil.success(response)
        } catch (ex: Exception) {
            return networkUtil.exception(ex)
        }
    }
}