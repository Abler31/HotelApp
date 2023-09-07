package com.example.hoteltest.di

import com.example.hoteltest.presentation.hotel.HotelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<HotelViewModel> {
        HotelViewModel(
            getHotelDataUseCase = get()
        )
    }

}