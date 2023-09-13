package com.example.hoteltest.presentation.booking

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.hoteltest.R
import com.example.hoteltest.presentation.booking.model.BookingAddTourist
import com.example.hoteltest.presentation.booking.model.BookingCustomer
import com.example.hoteltest.presentation.booking.model.BookingHotel
import com.example.hoteltest.presentation.booking.model.BookingPayButton
import com.example.hoteltest.presentation.booking.model.BookingPrice
import com.example.hoteltest.presentation.booking.model.BookingReservation
import com.example.hoteltest.presentation.booking.model.BookingTourist
import com.example.hoteltest.presentation.booking.model.DisplayableItem
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

object BookingFragmentDelegates {


    fun bookingHotelDelegate() = adapterDelegate<BookingHotel, DisplayableItem>(
        layout = R.layout.booking_recycler_hotel_item
        ){
        val rating = findViewById<TextView>(R.id.tv_booking_hotel_rating)
        val name = findViewById<TextView>(R.id.tv_booking_hotel_name)
        val address = findViewById<TextView>(R.id.tv_booking_hotel_address)

        bind {
            rating.text = item.rating
            name.text = item.name
            address.text = item.address
        }
    }


    fun bookingReservationDelegate() = adapterDelegate<BookingReservation, DisplayableItem>(
        layout = R.layout.booking_recycler_reservation_item
    ){
        val departure = findViewById<TextView>(R.id.tv_booking_departure)
        val country = findViewById<TextView>(R.id.tv_booking_country)
        val numberOfNights = findViewById<TextView>(R.id.tv_booking_number_of_nights)
        val dates = findViewById<TextView>(R.id.tv_booking_dates)
        val room = findViewById<TextView>(R.id.tv_booking_room)
        val hotel = findViewById<TextView>(R.id.tv_booking_hotel)
        val nutrition = findViewById<TextView>(R.id.tv_booking_nutrition)

        bind { diffPayloads ->
            departure.text = item.departure
            country.text = item.country
            numberOfNights.text = item.country
            dates.text = item.dates
            room.text = item.room
            hotel.text = item.hotel
            nutrition.text = item.nutrition
        }
    }

    fun bookingCustomerDelegate() = adapterDelegate<BookingCustomer, DisplayableItem>(
        layout = R.layout.booking_recycler_customer_item
    ){
        val phoneEditText = findViewById<TextInputEditText>(R.id.et_phone_number)
        val emailEditText = findViewById<TextInputEditText>(R.id.et_email)
        val layoutEmail = findViewById<TextInputLayout>(R.id.input_layout_email)
        emailEditText.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus && (emailEditText.text.isNullOrEmpty()
                        || !Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString()).matches())){
                layoutEmail.boxBackgroundColor = context.getColor(R.color.error_color)
            } else {
                layoutEmail.boxBackgroundColor = context.getColor(R.color.grey_field)
            }
        }
        
        phoneEditText.addTextChangedListener(object : TextWatcher {
            private var mSelfChange = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s == null || mSelfChange) {
                    return
                }

                val preparedStr = s.replace(Regex("(\\D*)"), "")
                var resultStr = ""

                for (i in preparedStr.indices) {
                    resultStr = when (i) {
                        0 -> resultStr.plus("+7 (")
                        1 -> resultStr.plus(preparedStr[i])
                        2 -> resultStr.plus(preparedStr[i])
                        3 -> resultStr.plus(preparedStr[i])
                        4 -> resultStr.plus(") ".plus(preparedStr[i]))
                        5 -> resultStr.plus(preparedStr[i])
                        6 -> resultStr.plus(preparedStr[i])
                        7 -> resultStr.plus("-".plus(preparedStr[i]))
                        8 -> resultStr.plus(preparedStr[i])
                        9 -> resultStr.plus("-".plus(preparedStr[i]))
                        10 -> resultStr.plus(preparedStr[i])
                        else -> resultStr
                    }
                }

                mSelfChange = true
                val oldSelectionPos = phoneEditText.selectionStart
                val isEdit = phoneEditText.selectionStart != phoneEditText.length()
                phoneEditText.setText(resultStr)
                if (isEdit) {
                    phoneEditText.setSelection(if (oldSelectionPos > resultStr.length) resultStr.length else oldSelectionPos)
                } else {
                    phoneEditText.setSelection(resultStr.length)
                }
                mSelfChange = false
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        bind { diffPayloads ->

        }
    }

    fun bookingTouristDelegate() = adapterDelegate<BookingTourist, DisplayableItem>(
        layout = R.layout.booking_recycler_tourist_item
    ){
        val btn = findViewById<CardView>(R.id.btn_booking_expand)
        val image = findViewById<ImageView>(R.id.iv_booking_arrow)
        val name = findViewById<TextInputLayout>(R.id.text_input_tourist_name)
        val surname = findViewById<TextInputLayout>(R.id.text_input_tourist_surname)
        val birthday = findViewById<TextInputLayout>(R.id.text_input_tourist_birthday)
        val citizenship = findViewById<TextInputLayout>(R.id.text_input_tourist_citizenship)
        val passport = findViewById<TextInputLayout>(R.id.text_input_tourist_passport)
        val expiration = findViewById<TextInputLayout>(R.id.text_input_tourist_expiration)
        val touristNumber = findViewById<TextView>(R.id.tv_tourist_number)


        btn.setOnClickListener {
            if (!item.expanded){
                item.expanded = true
                image.setImageResource(R.drawable.icon_arrow_up)
                name.visibility = View.VISIBLE
                surname.visibility = View.VISIBLE
                birthday.visibility = View.VISIBLE
                citizenship.visibility = View.VISIBLE
                passport.visibility = View.VISIBLE
                expiration.visibility = View.VISIBLE
            }else{
                item.expanded = false
                image.setImageResource(R.drawable.icon_arrow_down)
                name.visibility = View.GONE
                surname.visibility = View.GONE
                birthday.visibility = View.GONE
                citizenship.visibility = View.GONE
                passport.visibility = View.GONE
                expiration.visibility = View.GONE
            }

        }
        bind { diffPayloads ->
            touristNumber.text = item.toOrdinal(item.touristNumber).plus(" турист")
        }
    }

    fun bookingAddTouristDelegate(itemClickedListener: () -> Unit) = adapterDelegate<BookingAddTourist, DisplayableItem>(
        layout = R.layout.booking_recycler_add_tourist_item
    ){
        val btn = findViewById<CardView>(R.id.btn_booking_add)
        btn.setOnClickListener {
            BookingTourist.counter ++
            itemClickedListener()
        }
        bindingAdapter
        bind { diffPayloads ->
        }
    }

    fun bookingPriceDelegate() = adapterDelegate<BookingPrice, DisplayableItem>(
        layout = R.layout.booking_recycler_price_item
    ){
        val tourPrice = findViewById<TextView>(R.id.tv_booking_tour_price)
        val fuelCharge = findViewById<TextView>(R.id.tv_booking_fuel_charge)
        val serviceCharge = findViewById<TextView>(R.id.tv_booking_service_charge)
        val summary = findViewById<TextView>(R.id.tv_booking_sum)

        bind { diffPayloads ->
            tourPrice.text = item.tourPrice
            fuelCharge.text = item.fuelCharge
            serviceCharge.text = item.serviceCharge
            summary.text = item.summary
        }
    }

    fun bookingPayButtonDelegate() = adapterDelegate<BookingPayButton, DisplayableItem>(
        layout = R.layout.booking_recycler_pay_button_item
    ){
        val btn = findViewById<MaterialButton>(R.id.btn_booking_to_paid)

        bind { diffPayloads ->
            btn.text = item.sumText
        }
    }

}