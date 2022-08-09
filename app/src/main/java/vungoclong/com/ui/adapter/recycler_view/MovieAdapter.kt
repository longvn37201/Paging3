package vungoclong.com.ui.adapter.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Precision
import vungoclong.com.R
import vungoclong.com.data.model.Movie
import vungoclong.com.databinding.ItemMovieBinding

class MovieAdapter : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieComparator) {

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                imgPoster.load(movie.getFullPosterImagePath()) {
                    placeholder(R.drawable.ic_launcher_foreground)
                    precision(Precision.AUTOMATIC)
                }
                tvTitle.text = movie.originalTitle
                tvVoteCount.text = "Vote Count: ${movie.voteCount}"
                tvDate.text = "Release Date: ${movie.releaseDate}"
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MovieViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it)
        }
    }

    companion object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ) = oldItem == newItem
    }

}
