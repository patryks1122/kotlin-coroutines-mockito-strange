package github

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.IllegalStateException

interface GithubService {
    @GET("/users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String) : List<Repo>

    @GET("/orgs/{org}/repos")
    suspend fun getOrgRepos(@Path("org") org: String) : List<Repo>

    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun getRepoContributors(@Path("owner") owner: String, @Path("repo") repo: String): List<User>
}

val authToken = System.getenv("GITHUB_AUTH_TOKEN") ?: throw IllegalStateException("specify GITHUB_AUTH_TOKEN environment")

val client : OkHttpClient = OkHttpClient().newBuilder()
    .addInterceptor {
        chain ->
        val original = chain.request()
        val builder = original.newBuilder()
            .header("Accept", "application/vnd.github.v3+json")
            .header("Authorization", "token $authToken")
        val request = builder.build()
        chain.proceed(request)
    }
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = BASIC
    })
    .build()§

val githubService : GithubService = Retrofit.Builder()
    .client(client)
    .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
    .baseUrl("https://api.github.com")
    .build()
    .create(GithubService::class.java)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repo(
    val id: Long,
    val name: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    val login: String,
    val contributions: Int
)