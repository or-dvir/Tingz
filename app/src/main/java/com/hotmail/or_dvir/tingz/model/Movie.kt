package com.hotmail.or_dvir.tingz.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

//for the purposes of this exercise, assume all values are not null
@Entity(tableName = "table_movies")
@JsonIgnoreProperties(ignoreUnknown = true)
data class Movie(@PrimaryKey(autoGenerate = true)
                 val id: Long,
                 val genre: List<String>,
                 val image: String,
                 val rating: Float,
                 val releaseYear: Int,
                 val title: String)
    : Serializable