package com.example.hoteltest.presentation.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Resource
import com.example.domain.models.BookingModel
import com.example.domain.usecases.GetBookingDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookingViewModel(
    private val getBookingDataUseCase: GetBookingDataUseCase
) : ViewModel() {

    private val _bookingData = MutableLiveData<Resource<BookingModel>>()
    val bookingData: LiveData<Resource<BookingModel>> = _bookingData

    fun getHotelData() = viewModelScope.launch(Dispatchers.IO) {
        _bookingData.postValue(Resource.Loading())

        _bookingData.postValue(getBookingDataUseCase.execute())
    }

}