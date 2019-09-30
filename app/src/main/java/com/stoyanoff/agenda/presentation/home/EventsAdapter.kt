/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stoyanoff.agenda.R
import com.stoyanoff.agenda.data.model.CalendarEvent

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventHolder>() {

    lateinit var clickListener: ((CalendarEvent) -> Unit)
    private var events = mutableListOf<CalendarEvent>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.agenda_item_view,parent,false)
        return EventHolder(view)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bindEventItem(events[position])
    }

    fun setItems(items: MutableList<CalendarEvent>) {
        events = items
        notifyDataSetChanged()
    }

    fun addItem(item : CalendarEvent) {
        events.add(item)
        notifyItemInserted(events.indexOf(item))
    }


    inner class EventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var eventTitle = itemView.findViewById<TextView>(R.id.eventTitle)
        private var eventColor = itemView.findViewById<ImageView>(R.id.eventColor)
        private var event : CalendarEvent? = null


        fun bindEventItem(event: CalendarEvent) {
            eventTitle.text = event.title
            eventColor.setBackgroundColor(event.calendarColor)
            this.event = event
        }

        init{
            itemView.setOnClickListener{
                event?.let {
                    clickListener?.invoke(event!!)
                }
            }
        }
    }
}