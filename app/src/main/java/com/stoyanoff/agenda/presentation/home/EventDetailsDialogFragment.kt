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
import kotlinx.android.synthetic.main.details_dialog.*
import kotlinx.android.synthetic.main.details_dialog.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by L on 30/09/2019.
 *  All rights reserved.
 */
class EventDetailsDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.details_dialog,container,false)

        with(EventDetailsDialogFragmentArgs.fromBundle(arguments)){
            fragmentView.title.text = eventData.title
            fragmentView.calendar.text = eventData.calendarName
            fragmentView.time.text = getDate(eventData.startTime.toLong())
            fragmentView.location.text = eventData.location
            fragmentView.notes.text = eventData.description
            fragmentView.attendees.text = eventData.attendees.toString()

        }

        return fragmentView
    }

    private fun getDate(milliSeconds: Long): String {
        val formatter = SimpleDateFormat(
            "dd/MM/yyyy hh:mm:ss a", Locale.getDefault()
        )
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }


}