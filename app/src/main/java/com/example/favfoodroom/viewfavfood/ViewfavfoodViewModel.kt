package com.example.favfoodroom.viewfavfood

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.favfoodroom.database.Food
import com.example.favfoodroom.database.FoodDatabaseDao
import kotlinx.coroutines.launch

class ViewfavfoodViewModel(
val database: FoodDatabaseDao,
application: Application
) : AndroidViewModel(application) {

    val allFood=database.getAllFood()       //Room uses a background thread for queries which returns LiveData

    val allFoodZ:LiveData<List<Food>>
        get() {
            return allFood
        }


    private val _foodItem= MutableLiveData<Food>()
    val foodItem: LiveData<Food>
        get() = _foodItem

    fun putValueToFoodItem(food: Food) {
        _foodItem.value = food
    }

    fun setFoodItemAsNull(){
        _foodItem.value = null
    }




    // Event action which triggers the end of the all facts and tells whether to go start fragment or not
    private val _eventStartPressed = MutableLiveData<Boolean>()
    val eventStartPressed: LiveData<Boolean>
        get() = _eventStartPressed


    fun setEventStartPressedToFalse() {
        _eventStartPressed.value = false
    }

    fun setEventStartPressedToTrue() {
        _eventStartPressed.value = true
    }

    init{

        setEventStartPressedToFalse()
        setFoodItemAsNull()
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

    suspend fun clear() {
        database.clear()

    }

}

