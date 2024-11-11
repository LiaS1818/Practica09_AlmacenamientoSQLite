package com.example.practica09_almacenamientosqlite.entities.guardaBosques

import com.example.practica09_almacenamientosqlite.db.GuardaBosqueDatabaseHelper

class GuardaBosquesRepository (private val guardaBosquesDatabaseHelper: GuardaBosqueDatabaseHelper) {

    fun saveGuardaBosque(guardaBosque: GuardaBosques) : Boolean {
        val value  = guardaBosquesDatabaseHelper.saveGuardaBosques(guardaBosque)
        return value != -1L
    }

    fun deleteGuardaBosque(id: Int) {

    }

    fun updateGuardaBosque(guardaBosques: GuardaBosques) {
        guardaBosquesDatabaseHelper.updateGuardaBosques(guardaBosques)
    }

    fun getAllGuardaBosques(): List<GuardaBosques> {
        return guardaBosquesDatabaseHelper.getAllGuardaBosques()

    }
}