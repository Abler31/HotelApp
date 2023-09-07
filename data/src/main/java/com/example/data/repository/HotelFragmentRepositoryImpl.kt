package com.example.data.repository

import com.example.data.AboutTheHotelToDomainMapper
import com.example.data.HotelModelToDomainMapper
import com.example.data.models.HotelModelEntity
import com.example.data.network.NetworkApi
import com.example.domain.Resource
import com.example.domain.models.HotelModelDomain
import com.example.domain.repository.HotelFragmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class HotelFragmentRepositoryImpl(private val networkApi: NetworkApi): HotelFragmentRepository {
    override suspend fun getHotelData(): Resource<HotelModelDomain> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<HotelModelEntity> = networkApi.getHotelData()
                if (response.isSuccessful) {
                    val data = HotelModelToDomainMapper(AboutTheHotelToDomainMapper())
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