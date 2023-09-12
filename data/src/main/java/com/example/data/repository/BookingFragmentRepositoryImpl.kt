package com.example.data.repository

import com.example.data.mappers.AboutTheHotelToDomainMapper
import com.example.data.mappers.BookingModelToDomainMapper
import com.example.data.mappers.HotelModelToDomainMapper
import com.example.data.models.BookingModelEntity
import com.example.data.models.HotelModelEntity
import com.example.data.network.NetworkApi
import com.example.domain.Resource
import com.example.domain.models.BookingModel
import com.example.domain.repository.BookingFragmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class BookingFragmentRepositoryImpl(private val networkApi: NetworkApi): BookingFragmentRepository {
    override suspend fun getBookingData(): Resource<BookingModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<BookingModelEntity> = networkApi.getBookingData()
                if (response.isSuccessful) {
                    val data = BookingModelToDomainMapper()
                        .transform(response.body()!!)
                    Resource.Success(data = data)
                } else {
                    Resource.Error(errorMessage = response.errorBody().toString())
                }

            } catch (e: HttpException) {
                Resource.Error(errorMessage = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                Resource.Error("Please check your network connection")
            } catch (e: Exception) {
                Resource.Error(errorMessage = "Something went wrong")
            }
        }
    }
}