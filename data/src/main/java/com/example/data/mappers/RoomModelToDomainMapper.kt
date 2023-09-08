package com.example.data.mappers

import Room
import RoomModel
import com.example.data.models.RoomEntity
import com.example.data.models.RoomModelEntity

class RoomModelToDomainMapper(
    private val roomEntityToDomainMapper: Mapper<RoomEntity, Room>
): Mapper<RoomModelEntity, RoomModel> {
    override fun transform(data: RoomModelEntity) = RoomModel(
        data.rooms.map{
            roomEntityToDomainMapper.transform(it)
        }
    )
}