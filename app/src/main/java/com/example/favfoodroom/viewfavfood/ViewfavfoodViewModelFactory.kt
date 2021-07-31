package com.example.favfoodroom.viewfavfood

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.favfoodroom.database.FoodDatabaseDao

class ViewfavfoodViewModelFactory(private val dataSource: FoodDatabaseDao,
                                          private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewfavfoodViewModel::class.java)) {
            return ViewfavfoodViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}