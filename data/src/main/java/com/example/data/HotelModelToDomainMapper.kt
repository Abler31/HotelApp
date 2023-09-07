package com.example.data

import com.example.data.models.AboutTheHotelEntity
import com.example.data.models.HotelModelEntity
import com.example.domain.Mapper
import com.example.domain.models.AboutTheHotel
import com.example.domain.models.HotelModel

class HotelModelToDomainMapper(
    private val aboutTheHotelEntityToDomainMapper: Mapper<AboutTheHotelEntity, AboutTheHotel>
): Mapper<HotelModelEntity, HotelModel>{
    override fun transform(data: HotelModelEntity) = HotelModel(
        aboutTheHotelEntityToDomainMapper.transform(data.about_the_hotel),
        data.adress,
        data.image_urls,
        data.minimal_price,
        data.name,
        data.price_for_it,
        data.rating,
        data.rating_name
    )
}