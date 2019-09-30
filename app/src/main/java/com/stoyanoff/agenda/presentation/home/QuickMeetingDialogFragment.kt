/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.stoyanoff.agenda.R
import kotlinx.android.synthetic.main.quick_meeting_dialog.*
import kotlinx.android.synthetic.main.quick_meeting_dialog.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by L on 30/09/2019.
 *  All rights reserved.
 */
class QuickMeetingDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.quick_meeting_dialog,container,false)

        with(QuickMeetingDialogFragmentArgs.fromBundle(arguments)){
            var time : String = try {
                 getDate(availableTime.toLong())
            } catch (e: NumberFormatException) {
                 "You are very busy"
            }
            fragmentView.quickMeetingValue.text = time

        }

        return fragmentView
    }

    private fun getDate(milliSeconds: Long): String {
        val formatter = SimpleDateFormat(
            "hh:mm", Locale.getDefault()
        )
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

}