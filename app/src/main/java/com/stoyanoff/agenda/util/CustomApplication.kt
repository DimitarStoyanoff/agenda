/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.util

import android.app.Application
import com.stoyanoff.agenda.inject.dataModule
import com.stoyanoff.agenda.inject.presentationModule
import org.koin.android.ext.android.startKoin

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
class CustomApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        initDependencyInjectionFramework()
    }

    private fun initDependencyInjectionFramework() {
        startKoin(this, listOf(
            presentationModule,
            dataModule
        ))
    }
}