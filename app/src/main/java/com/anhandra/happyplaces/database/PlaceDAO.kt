package com.anhandra.happyplaces.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlaceDAO {

    @Insert
    suspend fun insert(place: Place)

    @Update
    suspend fun update(place: Place)

    @Query("SELECT * from places_table Where placeId = :key")
    suspend fun get(key: Long): Place?

    @Query("Delete from places_table")
    suspend fun clear()

    @Query("SELECT * FROM places_table ORDER BY placeId DESC")
    fun getAllPlaces(): LiveData<List<Place>>

    @Query("SELECT * FROM places_table ORDER BY placeId DESC LIMIT 1")
    suspend fun getLastPlace(): Place?
}