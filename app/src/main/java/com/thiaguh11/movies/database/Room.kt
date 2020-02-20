package com.thiaguh11.movies.database

import android.content.Context
import androidx.room.*
import com.thiaguh11.movies.DAO.MovieDao

@Database(entities = [DatabaseMovie::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
}

private lateinit var INSTANCE: MoviesDatabase

fun getDatabase(context: Context): MoviesDatabase {
    synchronized(MoviesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MoviesDatabase::class.java,
                "movies").build()
        }
    }
    return INSTANCE
}