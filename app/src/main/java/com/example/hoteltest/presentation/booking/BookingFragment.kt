package com.example.hoteltest.presentation.booking

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hoteltest.R
import com.example.hoteltest.presentation.booking.model.BookingHotel
import com.example.hoteltest.presentation.booking.model.BookingTourist
import com.example.hoteltest.presentation.booking.model.DisplayableItem
import com.google.android.material.textfield.TextInputEditText
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class BookingFragment : Fragment(R.layout.fragment_booking) {
    lateinit var rv: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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