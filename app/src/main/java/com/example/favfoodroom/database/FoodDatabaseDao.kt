package com.example.favfoodroom.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDatabaseDao {

    @Insert
    suspend fun insert(food: Food)              //insert a food data item

    @Update
    suspend fun update(food: Food)              //update food data item


    @Query("DELETE FROM Food")
    suspend fun clear()

    @Query("SELECT * FROM Food ORDER BY Id DESC LIMIT 1")   //return night with value 1
    suspend fun getFood(): Food?


    @Query("SELECT * FROM Food ORDER BY Id DESC")         //return all the nights as a list
    fun getAllFood(): LiveData<List<Food>>


}