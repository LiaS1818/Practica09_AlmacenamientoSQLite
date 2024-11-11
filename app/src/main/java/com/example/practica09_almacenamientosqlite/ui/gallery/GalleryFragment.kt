package com.example.practica09_almacenamientosqlite.ui.gallery

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.practica09_almacenamientosqlite.ListadoActivity
import com.example.practica09_almacenamientosqlite.databinding.FragmentGalleryBinding
import com.example.practica09_almacenamientosqlite.db.ParqueDatabaseHelper
import com.example.practica09_almacenamientosqlite.entities.parque.Parque

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private var listaPaques: List<Parque> = listOf()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val nombreEditText: TextView = binding.editTextNombre
        val ubicacionEditText: TextView = binding.editTextUbicacion
        val areaEditText: TextView = binding.editTextArea
        val fechaEditText: TextView = binding.editTextFecha
        val descripcionEditText: TextView = binding.editTextDescripcion

        // Registrar el Parque
        binding.buttonRegistrar.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val ubicacion = ubicacionEditText.text.toString()
            val area = areaEditText.text.toString()
            val fecha = fechaEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()
            val parque = Parque(null, nombre, ubicacion, area, fecha, descripcion)
            galleryViewModel.insertParque(parque)
        }

        // Cargar los parques
        binding.buttonBuscar.setOnClickListener {
            // Pasar informacion a Listado Activity
            listaPaques = galleryViewModel.getAllParques()!! //
        //listaPaques = galleryViewModel.getParques(nombreEditText.text.toString())
            val intent = Intent(requireContext(), ListadoActivity::class.java)
            intent.putParcelableArrayListExtra("parques", ArrayList(listaPaques))
            startActivity(intent)
        }

        // Eliminar los parques
        binding.buttonEliminar.setOnClickListener {
            val nombreText = nombreEditText.text.toString()
            try {
                val id = nombreText.toInt() // Convierte el texto a un entero
                galleryViewModel.deleteParque(id) // Llama al método deleteParque con el id convertido
            } catch (e: NumberFormatException) {
                // Maneja el caso en que la conversión falle, mostrando un mensaje al usuario
                Toast.makeText(context, "Ingrese un número válido.", Toast.LENGTH_SHORT).show()
            }
        }

        // Actualizar el Parque
        binding.buttonEditar.setOnClickListener{
            val id = nombreEditText.getTag().toString().toInt()
            val nombre = nombreEditText.text.toString()
            val ubicacion = ubicacionEditText.text.toString()
            val area = areaEditText.text.toString()
            val fecha = fechaEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()
            val parque = Parque(id, nombre, ubicacion, area, fecha, descripcion)
            galleryViewModel.updateParque(parque)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}