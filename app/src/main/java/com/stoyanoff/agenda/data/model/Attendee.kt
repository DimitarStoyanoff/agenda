/*
 * All rights reserved.
 */

/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
@Parcelize
data class Attendee(val name: String = "", val email: String = "") : Parcelable