package com.example.hoteltest.presentation.booking.model

data class BookingTourist(
    var expanded: Boolean = false,
    var touristNumber: Int = 1
): DisplayableItem{
    companion object{
        var counter: Int = 1
    }

    fun toOrdinal(number: Int): String{
        return when(number){
            1 -> "Первый"
            2 -> "Второй"
            3 -> "Третий"
            4 -> "Четвертый"
            5 -> "Пятый"
            6 -> "Шестой"
            7 -> "Седьмой"
            8 -> "Восьмой"
            9 -> "Девятый"
            10 -> "Десятый"
            else -> "Какой-то"
        }
    }
}