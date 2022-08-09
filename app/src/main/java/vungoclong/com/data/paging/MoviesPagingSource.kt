package vungoclong.com.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import vungoclong.com.data.model.Movie
import vungoclong.com.data.remote.api.MovieApi
import java.io.IOException


class MoviesPagingSource(
    private val movieApi: MovieApi
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> =
        try {
            val currentPage = params.key ?: 1
            val response = movieApi.getDiscoverMovies(page = currentPage)
            Log.d("anhlongnghien", "$response")
            LoadResult.Page(
                data = response.body()?.results ?: emptyList(),
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: IOException) {
            Log.d("anhlongnghien", "io${e.toString()}")
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            Log.d("anhlongnghien", "http${exception.toString()}")
            LoadResult.Error(exception)
        }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}