package com.example.data.repository

import RoomModel
import com.example.data.mappers.AboutTheHotelToDomainMapper
import com.example.data.mappers.HotelModelToDomainMapper
import com.example.data.mappers.RoomEntityToDomainMapper
import com.example.data.mappers.RoomModelToDomainMapper
import com.example.data.models.HotelModelEntity
import com.example.data.models.RoomModelEntity
import com.example.data.network.NetworkApi
import com.example.domain.Resource
import com.example.domain.repository.RoomFragmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RoomRepositoryImpl(private val networkApi: NetworkApi): RoomFragmentRepository {
    override suspend fun getRoomData(): Resource<RoomModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<RoomModelEntity> = networkApi.getRoomData()
                if (response.isSuccessful) {
                    val data = RoomModelToDomainMapper(RoomEntityToDomainMapper())
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