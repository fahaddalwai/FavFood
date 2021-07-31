package com.example.favfoodroom.addfavfood

import android.app.Application
import androidx.lifecycle.*
import com.example.favfoodroom.database.Food
import com.example.favfoodroom.database.FoodDatabaseDao
import kotlinx.coroutines.launch

class AddfavfoodViewModel(
    val database: FoodDatabaseDao,
    application: Application
) : AndroidViewModel(application) {








    fun enterData() {
        viewModelScope.launch {
            val newFood = Food()
            insert(newFood)

        }

    }


    private suspend fun insert(newFood: Food) {
        database.insert(newFood)

    }

}