package com.hotmail.or_dvir.tingz.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.hotmail.or_dvir.tingz.db.daos.IDaoMovies
import com.hotmail.or_dvir.tingz.model.Movie
import com.hotmail.or_dvir.tingz.other.RoomConverters

@Database(entities = [Movie::class],
          version = 1,
          exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class Sdb : RoomDatabase()
{
    abstract fun daoMovies(): IDaoMovies

    companion object
    {
        private const val DATABASE_NAME = "database_tingz"

        @Volatile
        private var INSTANCE: Sdb? = null

        fun getInstance(context: Context): Sdb
        {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): Sdb
        {
            return Room.databaseBuilder(context.applicationContext,
                                        Sdb::class.java,
                                        DATABASE_NAME)
                .build()
        }
    }
}