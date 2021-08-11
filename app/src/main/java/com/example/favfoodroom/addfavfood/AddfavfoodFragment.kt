package com.example.favfoodroom.addfavfood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.favfoodroom.R
import com.example.favfoodroom.database.FoodDatabase
import com.example.favfoodroom.databinding.FragmentAddfavfoodBinding
import com.google.android.material.snackbar.Snackbar


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


        viewModel.putFoodHolderValue.observe(viewLifecycleOwner, {
            if(it){
                viewModel.updateFoodHolderValue()
                viewModel.putFoodHolderValueToFalse()

            }
        })

        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.Added_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                viewModel.doneShowingSnackbar()
            }
        })


        viewModel.goBackToPrevActivity.observe(viewLifecycleOwner,{
            if(it){
                goToPrevFragment()
            }
        })

        return binding.root
    }

    fun goToPrevFragment() {
        findNavController().navigate(R.id.action_addfavfoodFragment_to_viewfavfoodFragment)
        viewModel.setEventGoBackToFalse()
    }


}