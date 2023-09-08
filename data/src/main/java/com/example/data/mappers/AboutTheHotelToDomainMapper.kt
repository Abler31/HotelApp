package com.example.data.mappers

import com.example.data.models.AboutTheHotelEntity
import com.example.domain.models.AboutTheHotel

class AboutTheHotelToDomainMapper: Mapper<AboutTheHotelEntity, AboutTheHotel> {
    override fun transform(data: AboutTheHotelEntity) = AboutTheHotel(
        description = data.description,
        peculiarities = data.peculiarities
    )
}