package com.example.hoteltest.di

import com.example.data.network.NetworkApi
import com.example.data.repository.HotelFragmentRepositoryImpl
import com.example.domain.repository.HotelFragmentRepository
import com.example.hoteltest.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single { provideRetrofit() }
    factory { provideNetworkApi(retrofit = get()) }
    single<HotelFragmentRepository> { HotelFragmentRepositoryImpl(networkApi = get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideNetworkApi(retrofit: Retrofit): NetworkApi = retrofit.create(NetworkApi::class.java)
