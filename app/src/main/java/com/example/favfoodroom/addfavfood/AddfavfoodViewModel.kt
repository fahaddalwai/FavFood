package com.example.favfoodroom.addfavfood

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.favfoodroom.database.Food
import com.example.favfoodroom.database.FoodDatabaseDao
import com.example.favfoodroom.network.FoodApi
import com.example.favfoodroom.network.Photo
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class AddfavfoodViewModel(
    val database: FoodDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val _goBackToPrevActivity = MutableLiveData<Boolean>()
    val goBackToPrevActivity: LiveData<Boolean>
        get() = _goBackToPrevActivity

    fun setEventGoBackToFalse(){
        _goBackToPrevActivity.value=false
    }

    fun setEventGoBackToTrue(){
        _goBackToPrevActivity.value=true
    }

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
        setEventGoBackToFalse()
    }

    fun putFoodHolderValueToTrue(){
        _putFoodHolderValue.value = true
    }

    fun putFoodHolderValueToFalse(){
        _putFoodHolderValue.value = false
    }



    var foodHolder = Food()



    var photo: String = "nothing"


    fun updateFoodHolderValue(){
        foodHolder.Name=name.value
        foodHolder.FavFood=food.value
        getUrlPhoto(food.value.toString())
    }

    fun getUrlPhoto(item:String){
        viewModelScope.launch{
            try {
                val photoGetter= FoodApi.retrofitService.getPhoto(item)
                photo=photoGetter.urls.regular

                foodHolder.URL=photo

                Log.i("value", photo)
                enterData()
            }
            catch (e: Exception){
                Log.i("error",e.toString())
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
        _showSnackbarEvent.value=true
        setEventGoBackToTrue()
    }





}