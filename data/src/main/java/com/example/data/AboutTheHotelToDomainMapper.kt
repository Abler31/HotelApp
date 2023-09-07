package com.example.data

import com.example.data.models.AboutTheHotelEntity
import com.example.domain.Mapper
import com.example.domain.models.AboutTheHotelDomain

class AboutTheHotelToDomainMapper: Mapper<AboutTheHotelEntity, AboutTheHotelDomain> {
    override fun transform(data: AboutTheHotelEntity) = AboutTheHotelDomain(
        description = data.description,
        peculiarities = data.peculiarities
    )
}