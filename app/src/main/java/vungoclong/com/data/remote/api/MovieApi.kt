package vungoclong.com.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import vungoclong.com.data.model.MovieApiResponse

interface MovieApi {

    companion object {
        private const val API_KEY = "enter_your_api_key"
    }

    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
    ): Response<MovieApiResponse>

}