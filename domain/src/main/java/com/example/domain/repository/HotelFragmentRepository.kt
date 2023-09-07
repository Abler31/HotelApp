package com.example.domain.repository

import com.example.domain.Resource
import com.example.domain.models.HotelModel

interface HotelFragmentRepository {
    suspend fun getHotelData(): Resource<HotelModel>

}