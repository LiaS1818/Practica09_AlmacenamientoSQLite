package com.example.practica09_almacenamientosqlite.entities.parque

import android.os.Parcel
import android.os.Parcelable

data class Parque(
    val id: Int?,
    val nombre: String,
    val ubicacion: String,
    val area: String,
    val fecha: String,
    val descripcion: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombre)
        parcel.writeString(ubicacion)
        parcel.writeString(area)
        parcel.writeString(fecha)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Parque> {
        override fun createFromParcel(parcel: Parcel): Parque = Parque(parcel)
        override fun newArray(size: Int): Array<Parque?> = arrayOfNulls(size)
    }
}

