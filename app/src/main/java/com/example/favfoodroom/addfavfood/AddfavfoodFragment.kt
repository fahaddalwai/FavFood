package com.example.favfoodroom.addfavfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.favfoodroom.R
import com.example.favfoodroom.database.FoodDatabase
import com.example.favfoodroom.databinding.FragmentAddfavfoodBinding
import com.example.favfoodroom.viewfavfood.ViewfavfoodViewModelFactory


class AddfavfoodFragment : Fragment() {

    private lateinit var binding: FragmentAddfavfoodBinding
    private lateinit var viewModel: AddfavfoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_addfavfood, container, false)


        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).foodDatabaseDao

        val viewModelFactory = AddfavfoodViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(AddfavfoodViewModel::class.java)      //define instance of viewmodel using provider



        // Set the viewmodel for databinding - this allows the bound layout access
        // to all the data in the ViewModel
        binding.addfavfoodViewModel = viewModel
        binding.lifecycleOwner = this




        return binding.root
    }


}