package com.example.practica09_almacenamientosqlite.ui.gallery

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practica09_almacenamientosqlite.db.ParqueDatabaseHelper
import com.example.practica09_almacenamientosqlite.entities.parque.Parque
import com.example.practica09_almacenamientosqlite.entities.parque.ParqueRepository

class GalleryViewModel (application: Application) : AndroidViewModel(application) {

    private val parqueRepository = ParqueRepository(ParqueDatabaseHelper(application))

    private val _parques = MutableLiveData<List<Parque>>() // Lista de parques
    val parques: LiveData<List<Parque>> = _parques // LiveData sirve para observar cambios en la lista de parques

    @SuppressLint("SuspiciousIndentation")
    fun insertParque(parque: Parque) {
      val insertado =  parqueRepository.saveParque(parque)
        if (insertado){
            Toast.makeText(getApplication(), "Parque registrado", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(getApplication(), "Error al registrar parque", Toast.LENGTH_SHORT).show()
        }
    }

    fun loadParques() {
        _parques.value = parqueRepository.getAllParques() // Cargar todos los parques


    }

    fun getParques(nombreParque: String): List<Parque> {
       return parqueRepository.getParques(nombreParque)
    }

    fun getAllParques(): List<Parque>? {
        return parqueRepository.getAllParques()
    }

    fun deleteParque(id: Int) {
        val eliminado = parqueRepository.deleteParque(id)
        if (eliminado){
            Toast.makeText(getApplication(), "Parque eliminado", Toast.LENGTH_SHORT).show()
        }else{

            Toast.makeText(getApplication(), "Error al eliminar parque", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateParque(parque: Parque) {
        parqueRepository.updateParque(parque)
    }

}