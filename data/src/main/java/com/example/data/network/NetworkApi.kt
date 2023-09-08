package com.example.data.network

import com.example.data.models.HotelModelEntity
import com.example.data.models.RoomEntity
import com.example.data.models.RoomModelEntity
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {
    @GET("v3/35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotelData(): Response<HotelModelEntity>

    @GET("v3/f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getRoomData(): Response<RoomModelEntity>
}