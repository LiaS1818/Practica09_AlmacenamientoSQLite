package com.example.practica09_almacenamientosqlite

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.practica09_almacenamientosqlite.entities.guardaBosques.GuardaBosques
import com.example.practica09_almacenamientosqlite.entities.parque.Parque

class ListadoActivity : AppCompatActivity() {

    private lateinit var textoListado: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listadoparques)

        textoListado = findViewById(R.id.txtListadoParques)

        // Intent para recibir la lista de parques
        val inforRecibida = intent.extras
        if (inforRecibida != null) {
            if (inforRecibida.containsKey("parques")) {

                val listaDeParques = inforRecibida.getParcelableArrayList<Parque>("parques")
                mostrarLista(listaDeParques?.toTypedArray()) // Convierte a Array si `mostrarLista` requiere Array

            }else if (inforRecibida.containsKey("guardaBosques")){

                val listaGuardaBosques = inforRecibida.getParcelableArrayList<GuardaBosques>("guardaBosques")
                mostrarListaGuardaBosques(listaGuardaBosques?.toTypedArray()) // Convierte a Array si `mostrarLista` requiere Array
            }
             // Convierte a Array si `mostrarLista` requiere Array
        }

    }

    private fun mostrarListaGuardaBosques(listaDeGuardaBosques: Array<GuardaBosques>?) {
        if (listaDeGuardaBosques.isNullOrEmpty()) {
            textoListado.text = "No hay guardabosques disponibles para mostrar."
            return
        }

        textoListado.text = listaDeGuardaBosques.joinToString(separator = "\n------------------------------------------------\n") { guardaBosques ->
            """
            Id: ${guardaBosques.id}
            Nombre: ${guardaBosques.nombre}
            Apellido: ${guardaBosques.apellido}
            Edad: ${guardaBosques.edad}
            Fecha de Ingreso: ${guardaBosques.fechaIngreso}
            Area de Trabajo: ${guardaBosques.area}
            """.trimIndent()
        }
    }

    private fun mostrarLista(listaDeParques: Array<Parque>?) {
        if (listaDeParques.isNullOrEmpty()) {
            textoListado.text = "No hay parques disponibles para mostrar."
            return
        }

        textoListado.text = listaDeParques.joinToString(separator = "\n------------------------------------------------\n") { parque ->
            """
            Id: ${parque.id}
            Nombre: ${parque.nombre}
            Ubicación: ${parque.ubicacion}
            Área: ${parque.area}
            Fecha: ${parque.fecha}
            Descripción: ${parque.descripcion}
            """.trimIndent() // Elimina los espacios en blanco al inicio y al final de la cadena
        }
    }
}
