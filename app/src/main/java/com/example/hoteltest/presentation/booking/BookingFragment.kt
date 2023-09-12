package com.example.hoteltest.presentation.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Resource
import com.example.domain.models.BookingModel
import com.example.hoteltest.R
import com.example.hoteltest.presentation.booking.model.BookingAddTourist
import com.example.hoteltest.presentation.booking.model.BookingCustomer
import com.example.hoteltest.presentation.booking.model.BookingHotel
import com.example.hoteltest.presentation.booking.model.BookingPayButton
import com.example.hoteltest.presentation.booking.model.BookingPrice
import com.example.hoteltest.presentation.booking.model.BookingReservation
import com.example.hoteltest.presentation.booking.model.BookingTourist
import com.example.hoteltest.presentation.booking.model.DisplayableItem
import com.google.android.material.appbar.MaterialToolbar
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookingFragment : Fragment(R.layout.fragment_booking) {
    private val vm by viewModel<BookingViewModel>()
    lateinit var rv: RecyclerView
    val mList = mutableListOf<DisplayableItem>()
    val adapter = ListDelegationAdapter<List<DisplayableItem>>(
        BookingFragmentDelegates.bookingHotelDelegate(),
        BookingFragmentDelegates.bookingReservationDelegate(),
        BookingFragmentDelegates.bookingCustomerDelegate(),
        BookingFragmentDelegates.bookingTouristDelegate(),
        BookingFragmentDelegates.bookingAddTouristDelegate(),
        BookingFragmentDelegates.bookingPriceDelegate(),
        BookingFragmentDelegates.bookingPayButtonDelegate()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<MaterialToolbar>(R.id.appToolbar).setTitle("Бронирование")

        rv = view.findViewById(R.id.rv_booking)
        rv.layoutManager = LinearLayoutManager(requireContext())

        vm.bookingData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val data = it.data
                    setBookingData(data)
                }

                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }
            }
        }

        vm.getHotelData()
        rv.adapter = adapter
    }

    private fun setBookingData(data: BookingModel?) {
        mList.add(
            BookingHotel(
                data!!.horating.toString().plus(" ${data.rating_name}"),
                data.hotel_name,
                data.hotel_adress
            )
        )
        mList.add(
            BookingReservation(
                data.departure,
                data.arrival_country,
                data.tour_date_start.plus("-${data.tour_date_stop}"),
                data.number_of_nights.toString().plus(" ночей"),
                data.hotel_name,
                data.room,
                data.nutrition
            )
        )
        mList.add(BookingCustomer())
        mList.add(BookingTourist())
        mList.add(BookingAddTourist())
        val sumPrice = (data.tour_price + data.fuel_charge + data.service_charge).toString()
            .replace("(\\d)(?=(\\d{3})+$)".toRegex(), "$1 ")
            .plus(" ₽")
        mList.add(
            BookingPrice(
                data.tour_price.toString()
                    .replace("(\\d)(?=(\\d{3})+$)".toRegex(), "$1 ")
                    .plus(" ₽"),
                data.fuel_charge.toString()
                    .replace("(\\d)(?=(\\d{3})+$)".toRegex(), "$1 ")
                    .plus(" ₽"),
                data.service_charge.toString()
                    .replace("(\\d)(?=(\\d{3})+$)".toRegex(), "$1 ")
                    .plus(" ₽"),
                sumPrice
            )
        )
        mList.add(BookingPayButton("Оплатить ${sumPrice}"))
        adapter.items = mList
        adapter.notifyDataSetChanged()
    }
}