package com.anhandra.happyplaces.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val PLACE_ID_EXTRA = "placeExtra"

@Entity(tableName = "places_table")
data class Place(
    @PrimaryKey(autoGenerate = true)
    var placeId: Long = 0L,

    @ColumnInfo(name = "location_name")
    var placeName: String = "Default",

    @ColumnInfo(name = "date")
    var date: String = "dd-mm-yy",

    @ColumnInfo(name = "mood")
    var mood: Int = -1,

    @ColumnInfo(name = "activity_type")
    var activityType: String = "Default activity",

    @ColumnInfo(name = "place_type")
    var placeType: String = "Default place",

    @ColumnInfo(name = "location")
    var location: String = "Default location",

    @ColumnInfo(name = "note")
    var personalNote: String = "Default note"

) {
}