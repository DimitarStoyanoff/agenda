/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.inject

import com.stoyanoff.agenda.data.CalendarHandler
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
val dataModule = module {
    single { CalendarHandler(androidApplication().contentResolver) }
}