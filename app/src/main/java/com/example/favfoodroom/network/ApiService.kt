package com.example.favfoodroom.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL =
    "https://api.unsplash.com/"

private const val CLIENT_ID =
    "vt2wUeIzdHJb3YhZ5XvYoLeg_fr1LnNSaXX01vna440" //In normal scenario, keep this hidden

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface FoodApiService {
    @Headers("Authorization: Client-ID $CLIENT_ID")
    @GET("photos/random")
    suspend fun getPhoto(
        @Query("query") type: String,
    ): Photo
}


object FoodApi {
    val retrofitService: FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
}