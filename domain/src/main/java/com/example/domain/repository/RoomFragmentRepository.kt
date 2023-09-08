package com.example.domain.repository

import RoomModel
import com.example.domain.Resource

interface RoomFragmentRepository {

    suspend fun getRoomData(): Resource<RoomModel>

}