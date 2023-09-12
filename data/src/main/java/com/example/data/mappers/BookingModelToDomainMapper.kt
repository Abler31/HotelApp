package com.example.data.mappers

import com.example.data.models.BookingModelEntity
import com.example.domain.models.BookingModel

class BookingModelToDomainMapper: Mapper<BookingModelEntity, BookingModel> {
    override fun transform(data: BookingModelEntity) = BookingModel(
        arrival_country = data.arrival_country,
        departure = data.departure,
        fuel_charge = data.fuel_charge,
        horating = data.horating,
        hotel_adress = data.hotel_adress,
        hotel_name = data.hotel_name,
        id = data.id,
        number_of_nights = data.number_of_nights,
        nutrition = data.nutrition,
        rating_name = data.rating_name,
        room = data.room,
        service_charge = data.service_charge,
        tour_date_start = data.tour_date_start,
        tour_date_stop = data.tour_date_stop,
        tour_price = data.tour_price
    )
}