package com.example.hoteltest.di

import com.example.data.repository.RoomRepositoryImpl
import com.example.domain.usecases.GetHotelDataUseCase
import com.example.domain.usecases.GetRoomDataUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetHotelDataUseCase> {
        GetHotelDataUseCase(repository = get())
    }

    factory<GetRoomDataUseCase> {
        GetRoomDataUseCase(repository = get())
    }

}