package com.example.favfoodroom.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.favfoodroom.database.Food
import com.example.favfoodroom.database.FoodDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository(private val dao: FoodDatabaseDao) {

    val allFood= dao.getAllFood()

    suspend fun onClear() {
        withContext(Dispatchers.IO) {
            clear()
        }
    }

    private suspend fun clear() {
        dao.clear()

    }



   suspend fun insert(newFood: Food) {
        dao.insert(newFood)

    }


}