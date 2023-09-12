package com.example.domain.repository

import com.example.domain.Resource
import com.example.domain.models.BookingModel

interface BookingFragmentRepository {

    suspend fun getBookingData(): Resource<BookingModel>

}