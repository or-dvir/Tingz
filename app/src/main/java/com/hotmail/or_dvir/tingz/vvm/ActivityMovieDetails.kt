package com.hotmail.or_dvir.tingz.vvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hotmail.or_dvir.tingz.R
import com.hotmail.or_dvir.tingz.model.Movie
import com.hotmail.or_dvir.tingz.other.AdapterMovies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class ActivityMovieDetails : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        //for the purposes of this exercise, skip nullability check
        val movie = intent.getSerializableExtra(AdapterMovies.EXTRA_MOVIE) as Movie
        supportActionBar?.title = movie.title

        Picasso.get()
            .load(movie.image)
            .fit()
            .centerInside()
            .into(iv)

        //for the purposes of this exercise, we ignore the warnings
        tv_rating.text = "${getString(R.string.rating)} ${movie.rating}"
        tv_year.text = "${getString(R.string.year)} ${movie.releaseYear}"
        tv_genre.text = "${getString(R.string.genres)} ${movie.genre.joinToString()}"
    }
}
