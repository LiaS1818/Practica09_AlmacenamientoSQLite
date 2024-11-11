package com.example.practica09_almacenamientosqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.practica09_almacenamientosqlite.entities.guardaBosques.GuardaBosques

class GuardaBosqueDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "guarda_bosques.db"
        private const val DATABASE_VERSION = 1

        // Para tabla de GuardaBosques
        const val TABLE_NAME = "guarda_bosques"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_APELLIDO = "apellido"
        const val COLUMN_EDAD = "edad"
        const val COLUMN_FECHA_INGRESO = "fechaIngreso"
        const val COLUMN_AREA = "area"
        const val COLUMN_TELEFONO = "telefono"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_APELLIDO TEXT, " +
                "$COLUMN_EDAD INTEGER, " +
                "$COLUMN_FECHA_INGRESO TEXT, " +
                "$COLUMN_AREA TEXT, " +
                "$COLUMN_TELEFONO TEXT)"

        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS parques")
        db.execSQL("DROP TABLE IF EXISTS guarda_bosques")
        onCreate(db)
    }

    // Insertar datos en la tabla de GuardaBosques
    fun saveGuardaBosques(guardaBosques: GuardaBosques) : Long{
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, guardaBosques.nombre)
            put(COLUMN_APELLIDO, guardaBosques.apellido)
            put(COLUMN_EDAD, guardaBosques.edad)
            put(COLUMN_FECHA_INGRESO, guardaBosques.fechaIngreso)
            put(COLUMN_AREA, guardaBosques.area)
            put(COLUMN_TELEFONO, guardaBosques.telefono)
        } // Objeto para almacenar los valores a insertar

        return db.insert(TABLE_NAME, null, values)
    }

    // Consultar todos los registros de la tabla de GuardaBosques
    fun getAllGuardaBosques() : List<GuardaBosques> {
        val lista = mutableListOf<GuardaBosques>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        if(cursor.moveToFirst()){
            do {
                val guardaBosques = GuardaBosques (
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                  nombre =  cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                  apellido =   cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                  edad =   cursor.getString(cursor.getColumnIndexOrThrow("edad")),
                  fechaIngreso =   cursor.getString(cursor.getColumnIndexOrThrow("fechaIngreso")),
                  area =   cursor.getString(cursor.getColumnIndexOrThrow("area")),
                  telefono =   cursor.getString(cursor.getColumnIndexOrThrow("telefono"))
                )
                lista.add(guardaBosques)

            }while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    // Actualizar solo campos llenados, recibe un obejto de tipo GuardaBosques
    fun updateGuardaBosques(guardaBosques: GuardaBosques) : Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            if (guardaBosques.nombre != null) put(COLUMN_NOMBRE, guardaBosques.nombre)
            if (guardaBosques.apellido != null) put(COLUMN_APELLIDO, guardaBosques.apellido)
            if (guardaBosques.edad != null) put(COLUMN_EDAD, guardaBosques.edad)
            if (guardaBosques.fechaIngreso != null) put(COLUMN_FECHA_INGRESO, guardaBosques.fechaIngreso)
            if (guardaBosques.area != null) put(COLUMN_AREA, guardaBosques.area)
            if (guardaBosques.telefono != null) put(COLUMN_TELEFONO, guardaBosques.telefono)
        }
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(guardaBosques.id.toString())
        return db.update(TABLE_NAME, values, selection, selectionArgs)
    }

}
