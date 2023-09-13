package com.example.hoteltest.presentation.paid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.hoteltest.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import kotlin.random.Random

class PaidFragment : Fragment(R.layout.fragment_paid) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<MaterialToolbar>(R.id.appToolbar).setNavigationIcon(R.drawable.icon_back)
        requireActivity().findViewById<MaterialToolbar>(R.id.appToolbar).setTitle("Оплачено")

        val tvConfirm = view.findViewById<TextView>(R.id.tv_paid_confirm)
        val btnToHotel = view.findViewById<MaterialButton>(R.id.btn_to_hotel)
        val number = Random.nextInt(1,999999)
        val text = "Подтверждение заказа №${number} может занять некоторое время (от 1 часа до суток). " +
                "Как только мы получим ответ от туроператора, вам на почту придет уведомление."
        tvConfirm.text = text

        btnToHotel.setOnClickListener {
            findNavController().navigate(R.id.action_paid_to_hotel)
        }
    }

}