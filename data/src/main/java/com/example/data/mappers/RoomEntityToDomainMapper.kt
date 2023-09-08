package com.example.data.mappers

import Room
import com.example.data.models.RoomEntity

class RoomEntityToDomainMapper: Mapper<RoomEntity, Room> {
    override fun transform(data: RoomEntity) = Room(
        id = data.id,
        image_urls = data.image_urls,
        name = data.name,
        peculiarities = data.peculiarities,
        price = data.price,
        price_per = data.price_per
    )
}