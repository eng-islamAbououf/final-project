package com.finalProject.myapplication.Models

import android.os.Parcel
import android.os.Parcelable


data class DataP(
    val image: String,
    val name: String,
    val products: ArrayList<Product>
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        TODO("products")
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<DataP> {
        override fun createFromParcel(parcel: Parcel): DataP {
            return DataP(parcel)
        }

        override fun newArray(size: Int): Array<DataP?> {
            return arrayOfNulls(size)
        }
    }
}
