package com.hotmail.or_dvir.tingz.db.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.hotmail.or_dvir.tingz.model.Movie

@Dao
interface IDaoMovies
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>)

    @Query("select * from table_movies")
    fun getAllSync(): List<Movie>
}