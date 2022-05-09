package ru.serget.mybiglist.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllAwarding (
	val id : String,
	val coinPrice : Int,
	val iconUrl : String,
): Parcelable