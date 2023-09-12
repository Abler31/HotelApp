package com.example.hoteltest.presentation.booking.model

data class BookingReservation(
    val departure: String,
    val country: String,
    val dates: String,
    val number_of_nights: String,
    val hotel: String,
    val room: String,
    val nutrition: String
): DisplayableItem