package com.example.hoteltest.presentation.hotel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.domain.Resource
import com.example.hoteltest.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class HotelFragment : Fragment(R.layout.fragment_hotel) {

    private val vm by viewModel<HotelViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = view.findViewById<TextView>(R.id.tv_hotel_name)
        val adress = view.findViewById<TextView>(R.id.tv_hotel_adress)
        val minPrice = view.findViewById<TextView>(R.id.tv_hotel_min_price)
        val priceForIt = view.findViewById<TextView>(R.id.tv_hotel_price_for_it)
        val rating = view.findViewById<TextView>(R.id.tv_hotel_rating)
        val description = view.findViewById<TextView>(R.id.tv_hotel_description)
        val flow = view.findViewById<androidx.constraintlayout.helper.widget.Flow>(R.id.flow_hotel)

        vm.hotelData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val data = it.data
                    name.text = data?.name ?: ""
                    adress.text = data?.adress ?: ""
                    val minPriceStr = data?.minimal_price.toString()
                        .replace("(\\d)(?=(\\d{3})+$)".toRegex(), "$1 ")
                    minPrice.text = ("от $minPriceStr ₽ ")
                    priceForIt.text = data?.price_for_it.toString()
                        .replaceFirstChar {
                            it.lowercaseChar()
                        } ?: ""
                    rating.text = (data?.rating.toString() + " " + data?.rating_name) ?: ""
                    description.text = data?.about_the_hotel?.description ?: ""
                    data!!.about_the_hotel.peculiarities.forEachIndexed { i, str ->
                        val peculiaritiesView =
                            layoutInflater.inflate(R.layout.peculiarities_item, null)
                        peculiaritiesView.id = View.generateViewId()
                        val tv = peculiaritiesView.findViewById<TextView>(R.id.tv_peculiarity)
                        tv.text = str
                        view.findViewById<ConstraintLayout>(R.id.constraint_peculiarities)
                            .addView(peculiaritiesView, i)
                        flow.addView(peculiaritiesView)
                    }
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