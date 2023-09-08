package com.example.hoteltest.presentation.room

import RoomModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Resource
import com.example.domain.usecases.GetRoomDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(
    private val getRoomDataUseCase: GetRoomDataUseCase
): ViewModel() {

    private val _roomData = MutableLiveData<Resource<RoomModel>>()
    val roomData: LiveData<Resource<RoomModel>> = _roomData

    fun getRoomData() = viewModelScope.launch(Dispatchers.IO) {
        _roomData.postValue(Resource.Loading())

        _roomData.postValue(getRoomDataUseCase.execute())
    }

}