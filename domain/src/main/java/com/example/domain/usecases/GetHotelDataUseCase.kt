package com.example.domain.usecases

import com.example.domain.Resource
import com.example.domain.models.HotelModelDomain
import com.example.domain.repository.HotelFragmentRepository

class GetHotelDataUseCase(
    private val repository: HotelFragmentRepository
) {

    suspend fun execute(): Resource<HotelModelDomain>{
        return repository.getHotelData()
    }

}