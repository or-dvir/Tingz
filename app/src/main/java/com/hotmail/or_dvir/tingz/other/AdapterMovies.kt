package com.hotmail.or_dvir.tingz.other

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hotmail.or_dvir.tingz.R
import com.hotmail.or_dvir.tingz.model.Movie
import com.hotmail.or_dvir.tingz.vvm.ActivityMovieDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_movie.view.*

class AdapterMovies(private val mItems: List<Movie>)
    : RecyclerView.Adapter<AdapterMovies.ViewHolder>()
{
    companion object
    {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val item = mItems[position]

        holder.apply {
            Picasso.get()
                .load(item.image)
                .fit()
                .centerInside()
                .into(iv)

            tv_name.text = item.title
            tv_year.text = item.releaseYear.toString()
        }
    }

    override fun onViewRecycled(holder: ViewHolder)
    {
        super.onViewRecycled(holder)

        holder.apply {
            Picasso.get().cancelRequest(iv)
            tv_name.text = ""
            tv_year.text = ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val context = parent.context
        val itemView = LayoutInflater
                .from(context)
                .inflate(R.layout.list_item_movie, parent, false)

        val holder = ViewHolder(itemView)

        itemView.setOnClickListener {
            val intent =
                Intent(context, ActivityMovieDetails::class.java).apply {
                    putExtra(EXTRA_MOVIE, mItems[holder.adapterPosition])
                }

            context.startActivity(intent)
        }

        return holder
    }

    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val iv = itemView.iv
        val tv_name = itemView.tv_name
        val tv_year = itemView.tv_genre
    }
}