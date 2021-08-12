package com.example.favfoodroom.viewfavfood

import android.app.Application
import androidx.lifecycle.*
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

