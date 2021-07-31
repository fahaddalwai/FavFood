package com.example.favfoodroom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "Person Name")
    var Name: String? ="default",

    @ColumnInfo(name = "Food Name")
    var FavFood: String? ="default",

    )


