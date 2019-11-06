import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class NetworkUtil {
    fun getLoginToken(login: String, password: String) = "$login:$password"
    fun success(response: Response<String>) = response.body() ?: "null"
    fun exception(ex: Exception) = ex.message ?: "null"

}
