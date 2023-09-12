package com.example.domain.usecases

import com.example.domain.Resource
import com.example.domain.models.BookingModel
import com.example.domain.repository.BookingFragmentRepository

class GetBookingDataUseCase(
    private val repository: BookingFragmentRepository
) {

    suspend fun execute(): Resource<BookingModel> {
        return repository.getBookingData()
    }

}