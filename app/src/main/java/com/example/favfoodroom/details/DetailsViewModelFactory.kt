package com.example.favfoodroom.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.favfoodroom.database.Food

class DetailsViewModelFactory(
    private val food: Food,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(food, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}