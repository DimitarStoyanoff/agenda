/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.presentation.home

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.stoyanoff.agenda.R
import com.stoyanoff.agenda.presentation.common.BaseViewFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
class HomeFragment : BaseViewFragment() {

    val viewModel: HomeViewModel by viewModel()
    private val albumsAdapter: EventsAdapter by inject()

    companion object {
        private const val REQUEST_CODE_CALENDAR_READ = 100
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_home,container,false)
        setHasOptionsMenu(true)

        return fragmentView
    }

    private fun isPermissionGranted(activity: Activity, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun performPermissionCheck() {
        activity?.let { activity ->
            if (isPermissionGranted(activity, Manifest.permission.READ_CALENDAR)) {
                loadData()
            } else {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_CALENDAR),
                    REQUEST_CODE_CALENDAR_READ
                )
            }
        }
    }

    override fun initUi() {
        initAdapter()
        performPermissionCheck()
    }

    override fun initViewModelStates() {
        handleEventsViewState()
        handleNavigateToDetailsEvent()
        handleNavigateToQuickMeetingEvent()
    }

    override fun toggleLoading(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE
    }

    private fun initAdapter() {
        albumsAdapter.clickListener = {
            viewModel.listItemClicked(it)
        }

        with(recycler_view){
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            adapter = albumsAdapter
        }
    }

    private fun loadData() {
        viewModel.loadData()
    }


    private fun handleEventsViewState() {
        viewModel.viewState.observe(this, Observer {
            if(it != null) {
                toggleLoading(it.showLoading)

                it.events?.let {albums ->
                    albumsAdapter.setItems(albums)
                }
            }
        })
    }

    private fun handleNavigateToDetailsEvent() {
        viewModel.navigateToEventDetails.observe(this, Observer {
            it.getContentIfNotHandled()?.let {event ->
                val action = HomeFragmentDirections.actionHomeFragmentToEventDetailsDialogFragment(event)
                navigateTo(action = action)
            }

        })
    }

    private fun handleNavigateToQuickMeetingEvent() {
        viewModel.navigateToQuickMeetingDetails.observe(this, Observer {
            it.getContentIfNotHandled()?.let {time ->
                val action = HomeFragmentDirections.actionHomeFragmentToQuickMeetingDialogFragment(time)
                navigateTo(action = action)
            }

        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_CALENDAR_READ)
            if(grantResults.size == 1 &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadData()
            } else Toast.makeText(context,"Why?", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.quick_meeting -> {
                viewModel.showQuickMeeting()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

