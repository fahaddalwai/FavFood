package com.example.favfoodroom.addfavfood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.favfoodroom.repository.Repository

class AddfavfoodViewModelFactory(private val repository: Repository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddfavfoodViewModel::class.java)) {
            return AddfavfoodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}