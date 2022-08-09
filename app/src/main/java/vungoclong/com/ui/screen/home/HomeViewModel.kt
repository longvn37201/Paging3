package vungoclong.com.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import vungoclong.com.data.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    val moviesFlow = repository
        .getDiscoverMovies()
        .cachedIn(viewModelScope)
}