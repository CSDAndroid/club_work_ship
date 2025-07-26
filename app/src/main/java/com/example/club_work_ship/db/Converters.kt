package com.example.club_work_ship.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.sql.Date
import java.sql.Time

class Converters {

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        if (value == null) return null
        val listType: Type = object : TypeToken<List<String>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        if (list == null) return null
        val gson = Gson()
        return gson.toJson(list)
    }
    
    @TypeConverter
    fun fromIntList(value: String?): List<Int>? {
        if (value == null) return null
        val listType: Type = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromIntList(list: List<Int>?): String? {
        if (list == null) return null
        val gson = Gson()
        return gson.toJson(list)
    }
    
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
    
    @TypeConverter
    fun fromTimeString(value: String?): Time? {
        return value?.let { Time.valueOf(it) }
    }

    @TypeConverter
    fun timeToString(time: Time?): String? {
        return time?.toString()
    }
    
    @TypeConverter
    fun fromLong(value: Long?): Long? {
        return value
    }

    @TypeConverter
    fun longToLong(value: Long?): Long? {
        return value
    }
}