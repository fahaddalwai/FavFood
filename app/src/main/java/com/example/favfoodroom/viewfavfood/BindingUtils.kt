package com.example.favfoodroom.viewfavfood

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
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