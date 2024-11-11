package com.example.practica09_almacenamientosqlite.entities.parque

import com.example.practica09_almacenamientosqlite.db.ParqueDatabaseHelper

class ParqueRepository (private val parqueDatabaseHelper: ParqueDatabaseHelper) {

    fun saveParque(parque: Parque) : Boolean {
        val value  = parqueDatabaseHelper . saveParque (parque)
        if (value != -1L){
            return true
        }
        return false
    }

    fun getParques(nombreParque: String): List<Parque> {
        return parqueDatabaseHelper.getParques(nombreParque)
    }
//
//    fun getParqueById(id: Int): Parque {
////        return parqueDatabaseHelper.getParqueById(id)
//    }

    fun updateParque(parque: Parque) {
        parqueDatabaseHelper.updateParque(parque)
    }

    fun deleteParque(id: Int) : Boolean{
        val eliminado = parqueDatabaseHelper.deleteParque(id)
        return eliminado != -1
    }

    fun getAllParques(): List<Parque>? {
        return parqueDatabaseHelper.getAllParques()
    }
}