package com.example.favfoodroom.viewfavfood

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        Log.i("name", text as String)
    }
}


@BindingAdapter("FavFood")
fun TextView.setFavFood(item: Food?) {
    item?.let {
        text = item.FavFood
        Log.i("food", text as String)
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri =
            imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}


@BindingAdapter("listData")                         //here we don't do observe in fragment to change list but instead connect list data directly to the viewModel
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Food>?
) {

    val adapter = recyclerView.adapter as ViewAllAdapter
    adapter.submitList(data)

}