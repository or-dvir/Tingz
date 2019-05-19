package com.hotmail.or_dvir.tingz.other

import android.arch.persistence.room.TypeConverter
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectReader
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hotmail.or_dvir.tingz.model.Movie

object SJacksonMapper
{
    private val mapper = jacksonObjectMapper()
    val objWriter: ObjectWriter = mapper.writer()
    val readerListString: ObjectReader = mapper.readerFor(object: TypeReference<List<String>>(){})
    val readerListMovies: ObjectReader = mapper.readerFor(object: TypeReference<List<Movie>>(){})
}

class RoomConverters
{
    @TypeConverter
    fun toListString(value: String): List<String> = SJacksonMapper.readerListString.readValue<List<String>>(value)
    @TypeConverter
    fun toString(value: List<String>): String = SJacksonMapper.objWriter.writeValueAsString(value)
}