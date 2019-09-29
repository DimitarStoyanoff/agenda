/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.presentation.home

import com.stoyanoff.agenda.data.model.CalendarEvent

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
data class HomeViewState(var showLoading : Boolean = false,
                         var events : MutableList<CalendarEvent>? = null)