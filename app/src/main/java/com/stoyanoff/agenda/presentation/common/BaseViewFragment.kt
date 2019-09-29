/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.presentation.common

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.stoyanoff.agenda.util.Logger
import androidx.fragment.app.Fragment

abstract class BaseViewFragment : Fragment() {

    companion object {
        const val TAG = "BaseViewFragment"
    }

    protected lateinit var fragmentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModelStates()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    abstract fun initUi()

    abstract fun initViewModelStates()

    /**
     * Sometimes during animation between fragments,
     * the user can click on an clickListener that is not handled
     * by the Navigation.
     */
    protected fun navigateTo(@IdRes resId: Int? = null, action: NavDirections? = null) {
        if (resId != null) {
            try {
                Navigation.findNavController(fragmentView).navigate(resId)
            } catch (e: IllegalArgumentException) {
            }
        }

        if (action != null) {
            try {
                findNavController().navigate(action)
            } catch (e: IllegalArgumentException) {
                Logger.e(TAG, { "Clicked on clickListener not handled by the Navigation. Ignore." }, e)
            }
        }
    }

    /**
     * Sometimes during animation between fragments,
     * the user can click on an clickListener that is not handled
     * by the Navigation.
     */
    protected fun popBackStack() {
        try {
            Navigation.findNavController(fragmentView).popBackStack()
        } catch (e: IllegalArgumentException) {
            Logger.e(TAG, { "Clicked on clickListener not handled by the Navigation. Ignore." }, e)
        }
    }

    abstract fun toggleLoading(isVisible: Boolean)


}