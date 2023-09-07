package com.example.hoteltest.presentation.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Resource
import com.example.domain.models.HotelModel
import com.example.domain.usecases.GetHotelDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelViewModel(
    private val getHotelDataUseCase: GetHotelDataUseCase
): ViewModel() {

    private val _hotelData = MutableLiveData<Resource<HotelModel>>()
    val hotelData: LiveData<Resource<HotelModel>> = _hotelData

    fun getHotelData() = viewModelScope.launch(Dispatchers.IO) {
        _hotelData.postValue(Resource.Loading())

        _hotelData.postValue(getHotelDataUseCase.execute())
    }


}