package com.example.favfoodroom.addfavfood

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.favfoodroom.database.Food
import com.example.favfoodroom.database.FoodDatabaseDao
import kotlinx.coroutines.launch

class AddfavfoodViewModel(
    val database: FoodDatabaseDao,
    application: Application
) : AndroidViewModel(application) {



    // The current fact
    private val _name = MutableLiveData<String>()                  //encapsulating it
    val name: LiveData<String>
        get() = _name

    // The current fact
    private val _food = MutableLiveData<String>()                  //encapsulating it
    val food: LiveData<String>
        get() = _name




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
        foodHolder.Name=_name.value
        foodHolder.FavFood=_food.value
        enterData()
    }


    fun enterData() {


        Log.i("amongusss", name.value.toString())
        viewModelScope.launch {
            insert(foodHolder)

        }

    }


    private suspend fun insert(newFood: Food) {
        database.insert(newFood)

    }

    fun nameSetter(string: String) {
        _name.value=string
    }

    fun foodSetter(string: String) {
        _food.value=string
    }

}