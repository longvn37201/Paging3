package vungoclong.com.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import vungoclong.com.data.paging.MoviesPagingSource
import vungoclong.com.data.remote.api.MovieApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
) {

    fun getDiscoverMovies() = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = {
            MoviesPagingSource(movieApi)
        }
    ).flow

}