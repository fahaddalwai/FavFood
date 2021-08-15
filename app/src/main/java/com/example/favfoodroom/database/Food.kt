package com.example.favfoodroom.database



import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class Food(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "Person Name")
    var Name: String? ="default",

    @ColumnInfo(name = "Food Name")
    var FavFood: String? ="default",

    @ColumnInfo(name = "Food URL")
    var URL: String? ="none",

    @ColumnInfo(name= "Created Date")
    var date:String?="not set"
    ): Parcelable







