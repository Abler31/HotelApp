package com.example.hoteltest.di

import com.example.domain.usecases.GetHotelDataUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetHotelDataUseCase> {
        GetHotelDataUseCase(repository = get())
    }

}