/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.presentation.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.stoyanoff.agenda.R
import com.stoyanoff.agenda.presentation.common.BaseActivity

class HomeActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initNavigationComponents()

    }

    private fun initNavigationComponents() {
        val navigationFragment = supportFragmentManager
            .findFragmentById(R.id.nav_home_host_fragment) as NavHostFragment ?: return
        navController = navigationFragment.navController
    }
}
