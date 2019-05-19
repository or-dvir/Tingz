package com.hotmail.or_dvir.tingz.vvm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hotmail.or_dvir.tingz.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class ActivityMain : AppCompatActivity()
{
    //note:
    //kotlin co-routines are no longer "experimental"
    //but in the interest of saving time, i still used the experimental variant
    //as i would have to study the new way of using co-routines.

    companion object
    {
        const val EXTRA_MOVIES = "EXTRA_MOVIES"
    }

    private lateinit var mViewModel: ActivityMainViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProviders.of(this)
            .get(ActivityMainViewModel::class.java)

        mViewModel.mMovieList.observe(this, Observer {
            progBar.visibility = View.GONE
            startActivity<ActivityMovieList>(EXTRA_MOVIES to it)
        })
    }
}

