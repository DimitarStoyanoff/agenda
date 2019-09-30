/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.data

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import com.stoyanoff.agenda.data.model.Attendee
import com.stoyanoff.agenda.data.model.CalendarEvent

/**
 * Created by L on 30/09/2019.
 *  All rights reserved.
 */
// Projection array. Creating indices for this array instead of doing
// dynamic lookups improves performance.
class CalendarHandler(private val contentResolver: ContentResolver) {

    private val EVENTS_PROJECTION: Array<String> = arrayOf(
        CalendarContract.Events._ID,
        CalendarContract.Events.TITLE,
        CalendarContract.Events.DESCRIPTION,
        CalendarContract.Events.DTSTART,
        CalendarContract.Events.DTEND,
        CalendarContract.Events.EVENT_LOCATION,
        CalendarContract.Events.CALENDAR_DISPLAY_NAME,
        CalendarContract.Events.CALENDAR_COLOR,
        CalendarContract.Events.AVAILABILITY,
        CalendarContract.Events.ALL_DAY,
        CalendarContract.Events.HAS_ATTENDEE_DATA
    )

    private val ATTENDEES_PROJECTION : Array<String> = arrayOf(
        CalendarContract.Attendees.ATTENDEE_NAME,
        CalendarContract.Attendees.ATTENDEE_EMAIL
    )

    fun getCalendarEvents() : MutableList<CalendarEvent> {
         val list : MutableList<CalendarEvent> = ArrayList<CalendarEvent>()

         // Run query
         val uri: Uri = CalendarContract.Events.CONTENT_URI
         val selection: String = "((${CalendarContract.Calendars.ACCOUNT_NAME} = ?) AND (" +
                 "${CalendarContract.Calendars.ACCOUNT_TYPE} = ?) AND (" +
                 "${CalendarContract.Calendars.OWNER_ACCOUNT} = ?))"
         val selectionArgs: Array<String> = arrayOf("hera@example.com", "com.example", "hera@example.com")
         val cur: Cursor = contentResolver.query(uri, EVENTS_PROJECTION, null, null, null)

        // Use the cursor to step through the returned records
        while (cur.moveToNext()) {

            // Get the field values
            val id: Int = cur.getInt(0)
            val title: String = cur.getString(1)
            val description: String = cur.getString(2)
            val dateStart: String = cur.getString(3)
            val dateEnd: String = cur.getString(4)
            val eventLocation: String = cur.getString(5)
            val calendarDisplayName: String = cur.getString(6)
            val calendarColor: Int = cur.getInt(7)
            val availability: String = cur.getString(8)
            val isAllDay: Int = cur.getInt(9)
            val hasAttendee = cur.getInt(10)
            // Do something with the values...

            var attendeeList : MutableList<Attendee> = ArrayList<Attendee>()
            //FIXME condition fails, maybe rework to only one attendees query
//            if(hasAttendee == 0) {
                attendeeList = getEventAttendees(id.toString())
//            }
            list.add(CalendarEvent(id,0,title,description,dateStart,dateEnd,eventLocation,
                calendarDisplayName,calendarColor,availability,isAllDay, attendeeList))
        }
        cur.close()
         return list
    }


    private fun getEventAttendees(arrayId : String) : MutableList<Attendee> {
        val attendeeList : MutableList<Attendee> = ArrayList<Attendee>()
        val uri: Uri = CalendarContract.Attendees.CONTENT_URI
        val selection: String = "${CalendarContract.Attendees.EVENT_ID} = ?"
        val selectionArgs: Array<String> = arrayOf(arrayId)
        val cur: Cursor = contentResolver.query(uri, ATTENDEES_PROJECTION, selection, selectionArgs, null)

        while(cur.moveToNext()) {
            attendeeList.add(Attendee(cur.getString(0), cur.getString(1)))
        }
        cur.close()
        return attendeeList
    }



}