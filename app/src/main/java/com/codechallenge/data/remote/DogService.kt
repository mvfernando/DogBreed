package com.codechallenge.data.remote

import com.codechallenge.data.remote.dto.ImageResultDto
import com.codechallenge.data.remote.dto.NameResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DogService {

    @GET("breeds/list")
    suspend fun getBreeds(): NameResultDto

    @GET("breed/{name}/images/random")
    suspend fun getImage(@Path("name") name: String): ImageResultDto?

}