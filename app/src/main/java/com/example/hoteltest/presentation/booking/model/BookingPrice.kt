package com.example.hoteltest.presentation.booking.model

data class BookingPrice(
    val tourPrice: String,
    val fuelCharge: String,
    val serviceCharge: String,
    val summary: String
): DisplayableItem
