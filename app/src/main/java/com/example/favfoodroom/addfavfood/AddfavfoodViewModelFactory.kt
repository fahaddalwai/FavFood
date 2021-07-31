package com.example.favfoodroom.addfavfood

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.favfoodroom.database.FoodDatabaseDao

class AddfavfoodViewModelFactory(private val dataSource: FoodDatabaseDao,
                                          private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddfavfoodViewModel::class.java)) {
            return AddfavfoodViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}