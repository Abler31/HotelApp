package com.example.data.network

import com.example.data.models.HotelModelEntity
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {
    @GET("v3/35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotelData(): Response<HotelModelEntity>
}