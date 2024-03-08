package com.codechallenge.core.di

import com.codechallenge.data.remote.DogService
import com.codechallenge.data.repository.DogRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    private val retrofit = Retrofit.Builder().baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val dogService = retrofit.create(DogService::class.java)

    val dogRepository = DogRepositoryImpl(dogService)
}