package com.example.favfoodroom.viewfavfood

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.favfoodroom.database.Food

@BindingAdapter("Id")
fun TextView.setId(item: Food?) {
    item?.let {
        text = item.id.toString()
        Log.i("id", text as String)
    }
}


@BindingAdapter("Name")
fun TextView.setName(item: Food?) {
    item?.let {
        text = item.Name
        Log.i("name",text as String)
    }
}


@BindingAdapter("FavFood")
fun TextView.setFavFood(item: Food?) {
    item?.let {
        text=item.FavFood
        Log.i("food",text as String)
    }
}


@BindingAdapter("listData")                         //here we don't do observe in fragment to change list but instead connect list data directly to the viewModel
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Food>?) {

    val adapter = recyclerView.adapter as ViewAllAdapter
    adapter.submitList(data)

}