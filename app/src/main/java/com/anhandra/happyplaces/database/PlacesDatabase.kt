package com.anhandra.happyplaces.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Place::class], version = 1, exportSchema = false)
abstract class PlacesDatabase : RoomDatabase() {

    abstract val placeDatabaseDao: PlaceDAO

    companion object {
        @Volatile
        private var INSTANCE: PlacesDatabase? = null

        fun getInstance(context: Context): PlacesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlacesDatabase::class.java,
                        "happy_places_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}