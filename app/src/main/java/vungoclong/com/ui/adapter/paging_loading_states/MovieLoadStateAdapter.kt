package vungoclong.com.ui.adapter.paging_loading_states

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import vungoclong.com.databinding.LayoutLoadstateViewholderBinding

class MovieLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MovieLoadStateAdapter.MovieLoadStateViewHolder>() {

    inner class MovieLoadStateViewHolder(
        private val binding: LayoutLoadstateViewholderBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.retryText.isVisible = loadState is LoadState.Error
        }
    }

    override fun onBindViewHolder(holder: MovieLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = MovieLoadStateViewHolder(
        LayoutLoadstateViewholderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

}