package com.example.favfoodroom.viewfavfood

import androidx.lifecycle.*
import com.example.favfoodroom.repository.Repository
import com.example.favfoodroom.database.Food
import kotlinx.coroutines.launch

class ViewfavfoodViewModel(private val repository: Repository)  : ViewModel() {

    private val allFood =
        repository.allFood   //Room uses a background thread for queries which returns LiveData anyways

    val allFoodZ: LiveData<List<Food>>
        get() {
            return allFood
        }


    private val _foodItem = MutableLiveData<Food>()
    val foodItem: LiveData<Food>
        get() = _foodItem

    fun putValueToFoodItem(food: Food) {
        _foodItem.value = food
    }

    fun setFoodItemAsNull() {
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

    init {

        setEventStartPressedToFalse()
        setFoodItemAsNull()
    }



    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        repository.onClear()

    }



}

