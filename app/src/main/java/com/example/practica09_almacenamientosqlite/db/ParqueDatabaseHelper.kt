package com.example.practica09_almacenamientosqlite.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.practica09_almacenamientosqlite.entities.parque.Parque

class ParqueDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "parques.db"
        private const val DATABASE_VERSION = 1

        // Para tabla de Parques
        const val TABLE_NAME = "parques"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_UBICACION = "ubicacion"
        const val COLUMN_AREA = "area"
        const val COLUMN_FECHA_APERTURA = "fecha"
        const val COLUMN_DESCRIPCION = "descripcion"


    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_UBICACION TEXT, " +
                "$COLUMN_AREA REAL, " +
                "$COLUMN_FECHA_APERTURA TEXT, " +
                "$COLUMN_DESCRIPCION TEXT)"

        db.execSQL(createTableSQL)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS parques")
        db.execSQL("DROP TABLE IF EXISTS guarda_bosques")
        onCreate(db)
    }

    // Insertar datos en la tabla de Parques
    fun saveParque(parque: Parque) : Long{
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, parque.nombre)
            put(COLUMN_UBICACION, parque.ubicacion)
            put(COLUMN_AREA, parque.area)
            put(COLUMN_FECHA_APERTURA, parque.fecha)
            put(COLUMN_DESCRIPCION, parque.descripcion)
        } // Objeto para almacenar los valores a insertar

        return db.insert(TABLE_NAME, null, values)
    }

    // Buscar parques por nombre
    @SuppressLint("Recycle", "Range")
    fun getParques(nombre: String) : List<Parque> {
        val db = readableDatabase
        val parques = mutableListOf<Parque>() // mutableList para almacenar los parques encontrados
        val cursor = db.query(TABLE_NAME, null, "$COLUMN_NOMBRE = ?", arrayOf(nombre), null, null, null)

        if(cursor.moveToFirst()){
            do{
                val parque = Parque(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    ubicacion = cursor.getString(cursor.getColumnIndexOrThrow("ubicacion")),
                    area = cursor.getString(cursor.getColumnIndexOrThrow("area")),
                    fecha = cursor.getString(cursor.getColumnIndexOrThrow("fechaApertura")),
                    descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
                )
                parques.add(parque)
            } while(cursor.moveToNext()) // Mientras haya registros
        }
        cursor.close()
        return parques
    }

    // Método para obtener todos los parques
    fun getAllParques(): List<Parque> {
        val parques = mutableListOf<Parque>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val parque = Parque(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    ubicacion = cursor.getString(cursor.getColumnIndexOrThrow("ubicacion")),
                    area = cursor.getString(cursor.getColumnIndexOrThrow("area")),
                    fecha = cursor.getString(cursor.getColumnIndexOrThrow("fechaApertura")),
                    descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
                )
                parques.add(parque)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return parques
    }

    //Eliminar parque por id
    fun deleteParque(id: Int) : Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    //Actualizar parque
    fun updateParque(parque: Parque) : Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, parque.nombre)
            put(COLUMN_UBICACION, parque.ubicacion)
            put(COLUMN_AREA, parque.area)
            put(COLUMN_FECHA_APERTURA, parque.fecha)
            put(COLUMN_DESCRIPCION, parque.descripcion)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(parque.id.toString()))
    }

}