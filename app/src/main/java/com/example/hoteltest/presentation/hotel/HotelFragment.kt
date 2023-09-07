package com.example.hoteltest.presentation.hotel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.domain.Resource
import com.example.hoteltest.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HotelFragment : Fragment(R.layout.fragment_hotel) {

    private val vm by viewModel<HotelViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.hotelData.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    Log.d("test success", it.data!!.name)
                }
                is Resource.Error -> {
                    Log.d("test error", it.message!!)
                }
                is Resource.Loading -> {
                    Log.d("test", "Загрузка...")
                }
            }
        }
        vm.getHotelData()
    }

}