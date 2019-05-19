package com.hotmail.or_dvir.tingz.vvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hotmail.or_dvir.tingz.R
import com.hotmail.or_dvir.tingz.model.Movie
import com.hotmail.or_dvir.tingz.other.AdapterMovies
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.jetbrains.anko.longToast

class ActivityMovieList : AppCompatActivity()
{
    private lateinit var mAdapter: AdapterMovies

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        //for the purposes of this exercise, no need to check the casting
        val movies = intent.getSerializableExtra(ActivityMain.EXTRA_MOVIES) as List<Movie>?

        if (movies != null)
        {
            mAdapter = AdapterMovies(movies)
            rv.apply {
                addItemDecoration(DividerItemDecoration(this@ActivityMovieList, DividerItemDecoration.VERTICAL))
                layoutManager = LinearLayoutManager(this@ActivityMovieList, RecyclerView.VERTICAL, false)
                adapter = mAdapter
            }
        }

        else
            longToast(R.string.error)
    }
}
