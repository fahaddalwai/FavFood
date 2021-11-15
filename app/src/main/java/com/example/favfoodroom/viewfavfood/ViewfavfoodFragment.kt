package com.example.favfoodroom.viewfavfood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.favfoodroom.R
import com.example.favfoodroom.repository.Repository
import com.example.favfoodroom.database.FoodDatabase
import com.example.favfoodroom.databinding.FragmentViewfavfoodBinding
import javax.inject.Inject

class ViewfavfoodFragment : Fragment() {

    private lateinit var binding: FragmentViewfavfoodBinding
    @Inject
    lateinit var viewModel: ViewfavfoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_viewfavfood, container, false)


        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).foodDatabaseDao
        val repository = Repository(dataSource)

        val viewModelFactory = ViewfavfoodViewModelFactory(repository)
        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ViewfavfoodViewModel::class.java)      //define instance of viewmodel using provider


        // Set the viewmodel for databinding - this allows the bound layout access
        // to all the data in the ViewModel
        binding.viewfavfoodViewModel = viewModel
        binding.lifecycleOwner = this


        binding.recyclerView.adapter = ViewAllAdapter(ViewAllAdapter.ClickListener {
            viewModel.putValueToFoodItem(it)
        })


        viewModel.foodItem.observe(viewLifecycleOwner, {
            it?.let {
                findNavController().navigate(
                    ViewfavfoodFragmentDirections.actionViewfavfoodFragmentToDetailsFragment(
                        it
                    )
                )
                viewModel.setFoodItemAsNull()
            }
        })

        viewModel.eventStartPressed.observe(viewLifecycleOwner, {
            if (it) {
                gotoAddFragment()
            }
        })



        return binding.root

    }


    private fun gotoAddFragment() {
        findNavController().navigate(R.id.action_viewfavfoodFragment_to_addfavfoodFragment)
        viewModel.setEventStartPressedToFalse()
    }


}