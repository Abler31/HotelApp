package com.example.domain.usecases

import com.example.domain.Resource
import com.example.domain.models.HotelModel
import com.example.domain.repository.HotelFragmentRepository

class GetHotelDataUseCase(
    private val repository: HotelFragmentRepository
) {

    suspend fun execute(): Resource<HotelModel>{
        return repository.getHotelData()
    }

}