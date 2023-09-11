package com.example.hoteltest.presentation.booking

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import com.example.hoteltest.R
import com.google.android.material.textfield.TextInputEditText

class BookingFragment : Fragment(R.layout.fragment_booking) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneEditText = view.findViewById<TextInputEditText>(R.id.et_phone_number)
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
    }
}