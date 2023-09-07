package com.example.data

import com.example.data.models.AboutTheHotelEntity
import com.example.data.models.HotelModelEntity
import com.example.domain.Mapper
import com.example.domain.models.AboutTheHotelDomain
import com.example.domain.models.HotelModelDomain

class HotelModelToDomainMapper(
    private val aboutTheHotelEntityToDomainMapper: Mapper<AboutTheHotelEntity, AboutTheHotelDomain>
): Mapper<HotelModelEntity, HotelModelDomain>{
    override fun transform(data: HotelModelEntity) = HotelModelDomain(
        aboutTheHotelEntityToDomainMapper.transform(data.about_the_hotel),
        data.adress,
        data.id,
        data.image_urls,
        data.minimal_price,
        data.name,
        data.price_for_it,
        data.rating,
        data.rating_name
    )
}