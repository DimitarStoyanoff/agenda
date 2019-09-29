/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.presentation.common

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()

        this.disposeDisposables()
    }

    private fun disposeDisposables() {
        if (!this.compositeDisposable.isDisposed) {
            this.compositeDisposable.dispose()
        }
    }
}