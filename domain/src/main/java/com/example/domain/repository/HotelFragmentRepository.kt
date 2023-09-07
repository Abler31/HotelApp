package com.example.domain.repository

import com.example.domain.Resource
import com.example.domain.models.HotelModelDomain

interface HotelFragmentRepository {
    suspend fun getHotelData(): Resource<HotelModelDomain>

}