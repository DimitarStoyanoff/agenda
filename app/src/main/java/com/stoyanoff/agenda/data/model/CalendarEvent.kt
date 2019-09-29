/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.data.model

import com.stoyanoff.agenda.data.model.Attendee

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
data class CalendarEvent(val id: Int, val type: Int, val title: String, val description: String, val startTime: String,
                 val endTime: String, val location: String, val calendarName: String, val calendarColor: Int,
                 val availability: String, val allDayEvent: Int, val attendees: List<Attendee>)