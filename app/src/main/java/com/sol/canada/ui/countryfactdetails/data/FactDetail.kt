package com.sol.canada.ui.countryfactdetails.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "fact")
@Parcelize
data class FactDetail(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val description: String?,
    val imageHref: String?,
    val title: String?
):Parcelable