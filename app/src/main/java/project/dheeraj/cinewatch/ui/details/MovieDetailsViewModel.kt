package project.dheeraj.cinewatch.ui.details

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import project.dheeraj.cinewatch.data.model.Movie
import project.dheeraj.cinewatch.data.model.Resource
import project.dheeraj.cinewatch.data.model.VideoResponse
import project.dheeraj.cinewatch.data.repository.NetworkRepository
import timber.log.Timber.e
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class MovieDetailsViewModel : ViewModel() {

    private val repository = NetworkRepository()

    private val _name = MutableLiveData("Movie Name")
    private val _movie = MutableLiveData<Movie>()
    private val _videos = MutableLiveData<VideoResponse>()

    var movieName : MutableLiveData<String> = _name
    var movie : MutableLiveData<Movie> = _movie
    var videos : MutableLiveData<VideoResponse> = _videos

    fun loadCast(movie_id : Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            // Fetch data from remote
            val apiResponse = repository.getMovieCredits(movie_id)
            emit(Resource.success(apiResponse))
        } catch (e: Exception) {
            if (e is SocketTimeoutException)
                emit(Resource.error("Something went wrong!"))
        }
    }

    fun loadSimilar(movie_id : Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            // Fetch data from remote
            val apiResponse = repository.getSimilarMovies(movie_id)
            emit(Resource.success(apiResponse))
        } catch (e: Exception) {
            if (e is SocketTimeoutException)
                emit(Resource.error("Something went wrong!"))
        }
    }

    fun getVideos(movie_id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResponse = repository.getVideos(movie_id)
                videos.postValue(apiResponse)
            } catch (e: Exception) {
                e(e)
            }
        }

    }


}