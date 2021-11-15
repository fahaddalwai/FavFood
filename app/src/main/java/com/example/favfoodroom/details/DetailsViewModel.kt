package com.example.favfoodroom.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.favfoodroom.database.Food
import javax.inject.Inject

class DetailsViewModel (
    food: Food
) : ViewModel() {

    private val _selectedFood = MutableLiveData<Food>()
    val selectedFood: LiveData<Food>
        get() = _selectedFood

    init {
        _selectedFood.value = food
    }

}