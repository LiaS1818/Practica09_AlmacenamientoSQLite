package com.example.practica09_almacenamientosqlite.ui.slideshow

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practica09_almacenamientosqlite.db.GuardaBosqueDatabaseHelper
import com.example.practica09_almacenamientosqlite.entities.guardaBosques.GuardaBosques
import com.example.practica09_almacenamientosqlite.entities.guardaBosques.GuardaBosquesRepository

class SlideshowViewModel (application: Application) : AndroidViewModel(application) {

    private val guardaBosquesRepository = GuardaBosquesRepository(GuardaBosqueDatabaseHelper(application))

    private val _guardaBosques = MutableLiveData<List<GuardaBosques>>()

    fun saveGuardaBosques(guardaBosques: GuardaBosques) {
        val insertado = guardaBosquesRepository.saveGuardaBosque(guardaBosques)
        if (insertado){
            Toast.makeText(getApplication(), "GuardaBosque registrado", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(getApplication(), "Error al registrar GuardaBosque", Toast.LENGTH_SHORT).show()
        }
    }

    fun getGuardaBosques(): List<GuardaBosques> {
        return guardaBosquesRepository.getAllGuardaBosques()
    }

    fun deleteGuardaBosque(id: Int) {
        guardaBosquesRepository.deleteGuardaBosque(id)
    }

    fun updateGuardaBosque(guardaBosques: GuardaBosques) {
        guardaBosquesRepository.updateGuardaBosque(guardaBosques)
    }
}