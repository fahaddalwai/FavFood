package com.example.favfoodroom.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.favfoodroom.R
import com.example.favfoodroom.databinding.FragmentDetailsBinding
import com.example.favfoodroom.databinding.FragmentViewfavfoodBinding
import com.example.favfoodroom.viewfavfood.ViewfavfoodViewModel


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        val application = requireNotNull(activity).application
        val food = DetailsFragmentArgs.fromBundle(requireArguments()).foodItem

        Log.i("asf",food.toString())
        binding.lifecycleOwner = this


        val viewModelFactory = DetailsViewModelFactory(food, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailsViewModel::class.java)

        return binding.root
    }


}