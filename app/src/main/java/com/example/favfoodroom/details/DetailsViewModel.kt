package com.example.favfoodroom.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.favfoodroom.database.Food

class DetailsViewModel(food: Food,
                      app: Application
) : AndroidViewModel(app) {

    private val _selectedFood = MutableLiveData<Food>()
    val selectedFood: LiveData<Food>
        get() = _selectedFood

    init {
        _selectedFood.value = food
    }

}