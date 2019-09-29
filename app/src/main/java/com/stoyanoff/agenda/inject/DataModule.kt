/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.inject

import com.stoyanoff.agenda.data.LocalDataSource
import com.stoyanoff.agenda.data.LocalRepository
import org.koin.dsl.module.module

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
val dataModule = module {
    factory<LocalDataSource> { LocalRepository() }
}