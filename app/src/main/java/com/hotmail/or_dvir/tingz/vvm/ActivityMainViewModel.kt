package com.hotmail.or_dvir.tingz.vvm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.hotmail.or_dvir.tingz.db.Sdb
import com.hotmail.or_dvir.tingz.model.Movie
import com.hotmail.or_dvir.tingz.other.SJacksonMapper
import kotlinx.coroutines.CommonPool
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class ActivityMainViewModel(private val mApp: Application)
    : AndroidViewModel(mApp)
{
    companion object
    {
        private const val TAG = "ActivityMainViewModel"
    }

    private val mDaoMovies = Sdb.getInstance(mApp).daoMovies()

    val mMovieList = MutableLiveData<List<Movie>>()

    init { launch { getAllMovies() } }

    private suspend fun getAllMovies()
    {
        val list = withContext(CommonPool) { mDaoMovies.getAllSync() }

        mMovieList.postValue(
            if (list.isEmpty())
            {
                val moviesJson = withContext(CommonPool) { loadJSONFromAsset() }
                val movies = SJacksonMapper.readerListMovies.readValue<List<Movie>>(moviesJson)
                mDaoMovies.insert(movies)
                movies.sortedByDescending { it.releaseYear }
            }

            else
                list.sortedByDescending { it.releaseYear }
        )
    }

    private fun loadJSONFromAsset(): String?
    {
        return try
        {
            mApp.assets.open("movie_list.json").use {
                String(it.readBytes())
            }
        }

        catch (ex: IOException)
        {
            Log.e(TAG, ex.message, ex)
            null
        }
    }
}