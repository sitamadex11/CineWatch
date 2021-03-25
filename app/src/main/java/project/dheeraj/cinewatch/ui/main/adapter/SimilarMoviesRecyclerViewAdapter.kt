package project.dheeraj.cinewatch.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.coroutines.ExperimentalCoroutinesApi
import project.dheeraj.cinewatch.R
import project.dheeraj.cinewatch.data.model.Movie
import project.dheeraj.cinewatch.databinding.SimilarMovieCardBinding
import project.dheeraj.cinewatch.ui.details.MovieDetailsActivity
import project.dheeraj.cinewatch.utils.CONSTANTS

/**
 * Created by Dheeraj Kotwani on 26-03-2021.
 */

@ExperimentalCoroutinesApi
class SimilarMoviesRecyclerViewAdapter(
    val context : Context,
    val movies : ArrayList<Movie>
) : RecyclerView.Adapter<SimilarMoviesRecyclerViewAdapter.ViewHolder>() {



    class ViewHolder(val itemView : View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimilarMovieCardBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.similar_movie_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.movieImage.load(CONSTANTS.ImageBaseURL + movies[position].poster_path)

            binding.movieImage.setOnClickListener {
                MovieDetailsActivity.getStartIntent(context, movies[position])
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    private fun onItemClicked(movie : Movie, imageView : ImageView? = null) {

        MovieDetailsActivity.getStartIntent(context, movie)

    }

}