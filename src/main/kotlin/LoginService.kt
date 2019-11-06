import retrofit2.Call
import retrofit2.Response

interface LoginService {
    suspend fun loginAsync(logintToken: String) : Response<String>
}
