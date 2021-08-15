package com.example.favfoodroom.viewfavfood

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.favfoodroom.R
import com.example.favfoodroom.database.FoodDatabase
import com.example.favfoodroom.databinding.FragmentViewfavfoodBinding
import com.example.favfoodroom.network.FoodApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewfavfoodFragment : Fragment() {

    private lateinit var binding: FragmentViewfavfoodBinding
    private lateinit var viewModel: ViewfavfoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_viewfavfood, container, false)




        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).foodDatabaseDao

        val viewModelFactory = ViewfavfoodViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ViewfavfoodViewModel::class.java)      //define instance of viewmodel using provider



        // Set the viewmodel for databinding - this allows the bound layout access
        // to all the data in the ViewModel
        binding.viewfavfoodViewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = ViewAllAdapter()


        viewModel.eventStartPressed.observe(viewLifecycleOwner, {
            if(it){
                gotoNextFragment()
            }
        })



        return binding.root

    }

    private fun gotoNextFragment() {
        findNavController().navigate(R.id.action_viewfavfoodFragment_to_addfavfoodFragment)
        viewModel.setEventStartPressedToFalse()
    }


}