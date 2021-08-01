package com.example.favfoodroom.addfavfood

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.favfoodroom.database.Food
import com.example.favfoodroom.database.FoodDatabaseDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class AddfavfoodViewModel(
    val database: FoodDatabaseDao,
    application: Application
) : AndroidViewModel(application) {



    // The current fact
    var name = MutableLiveData<String>()





    var food = MutableLiveData<String>()


    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }


    private val _putFoodHolderValue = MutableLiveData<Boolean>()                  //encapsulating it
    val putFoodHolderValue: LiveData<Boolean>
        get() = _putFoodHolderValue

    init{
        putFoodHolderValueToFalse()
    }

    fun putFoodHolderValueToTrue(){
        _putFoodHolderValue.value = true
    }

    fun putFoodHolderValueToFalse(){
        _putFoodHolderValue.value = false
    }



    var foodHolder = Food()


    fun updateFoodHolderValue(){
        foodHolder.Name=name.value
        foodHolder.FavFood=food.value
        enterData()
    }


    private fun enterData() {



        viewModelScope.launch {
            insert(foodHolder)

        }

    }


    private suspend fun insert(newFood: Food) {
        database.insert(newFood)
        _showSnackbarEvent.value=true

    }





}