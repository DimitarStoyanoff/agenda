/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stoyanoff.agenda.data.CalendarHandler
import com.stoyanoff.agenda.data.model.CalendarEvent
import com.stoyanoff.agenda.presentation.common.BaseViewModel
import com.stoyanoff.agenda.presentation.common.Event
import kotlinx.coroutines.launch

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
//TODO tests
class HomeViewModel(
    private val homeViewState : HomeViewState,
    private val calendarHandler: CalendarHandler
) : BaseViewModel() {

    internal val viewState = MutableLiveData<HomeViewState>().apply {
        value = homeViewState
    }
    internal val navigateToEventDetails = MutableLiveData<Event<CalendarEvent>>()
    internal val navigateToQuickMeetingDetails = MutableLiveData<Event<String>>()
    private var events = mutableListOf<CalendarEvent>()

    internal fun loadData() {
        if(events.size == 0) {
            toggleLoadingState(true)

            viewModelScope.launch {
                events = calendarHandler.getCalendarEvents()
                viewState.value?.let {
                    val newState = homeViewState.copy(showLoading = false, events = events)
                    viewState.value = newState
                }
            }
        }
    }

    private fun toggleLoadingState(showLoading: Boolean) {
        viewState.value?.let {
            val newState = homeViewState.copy(showLoading = showLoading)
            viewState.value = newState
        }
    }

    internal fun listItemClicked(item : CalendarEvent) {
        navigateToEventDetails.value = Event(item)
    }

    internal fun showQuickMeeting() {
        viewModelScope.launch {
            navigateToQuickMeetingDetails.value = Event(calendarHandler.getClosestMeetingTime())
        }
    }
}