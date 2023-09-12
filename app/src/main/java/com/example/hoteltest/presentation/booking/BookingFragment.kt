package com.example.hoteltest.presentation.booking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Resource
import com.example.hoteltest.R
import com.example.hoteltest.presentation.booking.model.DisplayableItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookingFragment : Fragment(R.layout.fragment_booking) {
    private val vm by viewModel<BookingViewModel>()
    lateinit var rv: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.bookingData.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    val data = it.data

                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }
        vm.getHotelData()

        val mList = mutableListOf<DisplayableItem>(

        )

        rv = view.findViewById(R.id.rv_booking)
        rv.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ListDelegationAdapter<List<DisplayableItem>>(
            BookingFragmentDelegates.bookingHotelDelegate(),
            BookingFragmentDelegates.bookingReservationDelegate(),
            BookingFragmentDelegates.bookingCustomerDelegate(),
            BookingFragmentDelegates.bookingTouristDelegate(),
            BookingFragmentDelegates.bookingAddTouristDelegate(),
            BookingFragmentDelegates.bookingPriceDelegate()
        )
        adapter.items = mList
        rv.adapter = adapter

    }
}