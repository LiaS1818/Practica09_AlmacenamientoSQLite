package com.example.practica09_almacenamientosqlite.ui.slideshow

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
import com.example.practica09_almacenamientosqlite.databinding.FragmentSlideshowBinding
import com.example.practica09_almacenamientosqlite.entities.guardaBosques.GuardaBosques

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var listaGuardaBosques: List<GuardaBosques> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val nombre: TextView = binding.editTextNombre
        val apellido: TextView = binding.editTextApellido
        val edad: TextView = binding.editTextEdad
        val telefono: TextView = binding.editTextTelefono
        val fechaIngreso: TextView = binding.editTextFechaIngreso
        val area : TextView = binding.editTextText

        //Registrar el usuario
        binding.buttonRegistrar.setOnClickListener {
            val nombre = nombre.text.toString()
            val apellido = apellido.text.toString()
            val edad = edad.text.toString()
            val telefono = telefono.text.toString()
            val fechaIngreso = fechaIngreso.text.toString()
            val area = area.text.toString()
            val guardaBosques = GuardaBosques(null, nombre, apellido, edad, telefono, fechaIngreso, area)
            slideshowViewModel.saveGuardaBosques(guardaBosques)
        }

        //Buscar los usuarios
        binding.buttonBuscar.setOnClickListener {
            // Pasar informacion a Listado Activity
            listaGuardaBosques = slideshowViewModel.getGuardaBosques()
            val intent = Intent(requireContext(), ListadoActivity::class.java)
            intent.putParcelableArrayListExtra("guardaBosques", ArrayList(listaGuardaBosques))
            startActivity(intent)
        }

        // Actualizar el usuario
        binding.buttonEditar.setOnClickListener {
            val id = nombre.getTag().toString().toInt()
            val nombre = nombre.text.toString()
            val apellido = apellido.text.toString()
            val edad = edad.text.toString()
            val telefono = telefono.text.toString()
            val fechaIngreso = fechaIngreso.text.toString()
            val area = area.text.toString()
            val guardaBosques = GuardaBosques(id, nombre, apellido, edad, telefono, fechaIngreso, area)
            slideshowViewModel.updateGuardaBosque( guardaBosques)
        }

        // Eliminar el usuario
        binding.buttonEliminar.setOnClickListener {
            val nombreText = nombre.text.toString()
            try {
                val id = nombreText.toInt() // Convierte el texto a un entero
                slideshowViewModel.deleteGuardaBosque(id) // Llama al método deleteParque con el id convertido
            } catch (e: NumberFormatException) {
                // Maneja el caso en que la conversión falle, mostrando un mensaje al usuario
                Toast.makeText(context, "Ingrese un número válido.", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}