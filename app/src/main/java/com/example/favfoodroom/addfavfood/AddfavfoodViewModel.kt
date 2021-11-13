package com.example.favfoodroom.addfavfood

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.favfoodroom.database.Food
import com.example.favfoodroom.database.FoodDatabaseDao
import com.example.favfoodroom.network.FoodApi
import kotlinx.coroutines.launch

class AddfavfoodViewModel(
    val database: FoodDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val _goBackToPrevActivity = MutableLiveData<Boolean>()
    val goBackToPrevActivity: LiveData<Boolean>
        get() = _goBackToPrevActivity

    fun setEventGoBackToFalse() {
        _goBackToPrevActivity.value = false
    }

    private fun setEventGoBackToTrue() {
        _goBackToPrevActivity.value = true
    }

    // The current name of person
    var name = MutableLiveData<String>()


    var food = MutableLiveData<String>()


    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }


    private val _putFoodHolderValue = MutableLiveData<Boolean>()
    val putFoodHolderValue: LiveData<Boolean>
        get() = _putFoodHolderValue

    init {
        putFoodHolderValueToFalse()
        setEventGoBackToFalse()
    }

    fun putFoodHolderValueToTrue() {
        _putFoodHolderValue.value = true
    }

    fun putFoodHolderValueToFalse() {
        _putFoodHolderValue.value = false
    }


    private var foodHolder = Food()


    fun updateFoodHolderValue() {
        foodHolder.Name = name.value
        foodHolder.FavFood = food.value
        setUrlAndDatePhoto(food.value.toString())
    }

    private fun setUrlAndDatePhoto(item: String) {


        viewModelScope.launch {
            try {
                val photoAndDateGetter = FoodApi.retrofitService.getPhoto(item)

                foodHolder.URL = photoAndDateGetter.urls.regular
                foodHolder.date = photoAndDateGetter.createdAt

                enterData()
            } catch (e: Exception) {
                Log.i("error", e.toString())
            }
        }

    }


    private fun enterData() {
        viewModelScope.launch {
            insert(foodHolder)

        }

    }


    private suspend fun insert(newFood: Food) {
        database.insert(newFood)
        _showSnackbarEvent.value = true
        setEventGoBackToTrue()
    }


}