import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

open class NetworkUtil {
    open fun success(response: Response<String>) = response.body() ?: "null"
    open fun exception(ex: Exception?) = ex?.message ?: "null"
}
