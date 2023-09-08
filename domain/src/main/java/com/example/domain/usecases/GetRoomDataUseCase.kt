package com.example.domain.usecases

import RoomModel
import com.example.domain.Resource
import com.example.domain.repository.RoomFragmentRepository

class GetRoomDataUseCase(
    private val repository: RoomFragmentRepository
) {

    suspend fun execute(): Resource<RoomModel> {
        return repository.getRoomData()
    }

}