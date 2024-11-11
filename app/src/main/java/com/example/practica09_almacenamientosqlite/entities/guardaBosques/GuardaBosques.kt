package com.example.practica09_almacenamientosqlite.entities.guardaBosques

import android.os.Parcel
import android.os.Parcelable

data class GuardaBosques(
    val id: Int?,
    val nombre: String,
    val apellido: String,
    val edad: String,
    val fechaIngreso: String,
    val area: String,
    val telefono: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(edad)
        parcel.writeString(fechaIngreso)
        parcel.writeString(area)
        parcel.writeString(telefono)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GuardaBosques> {
        override fun createFromParcel(parcel: Parcel): GuardaBosques {
            return GuardaBosques(parcel)
        }

        override fun newArray(size: Int): Array<GuardaBosques?> {
            return arrayOfNulls(size)
        }
    }
}
