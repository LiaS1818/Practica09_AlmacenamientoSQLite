package com.example.practica09_almacenamientosqlite

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IngresoActivity : AppCompatActivity() {
    // Instacias
    private lateinit var myuser: EditText
    private lateinit var password: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnCancelar: Button
    private lateinit var checkBox: CheckBox



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)
        // Asociar instancias con componentes graficos
        myuser = findViewById(R.id.edtUser)
        password = findViewById(R.id.edtPassword)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnCancelar = findViewById(R.id.btnCancel)
        checkBox = findViewById(R.id.checkboxRememberMe)
        checkUserSession()
    }//onCreate

    fun onClick(view: View?){
        when(view?.id){
            R.id.btnIngresar -> ingresar()
            R.id.btnCancel -> cancelar()
        }
    }

    private fun cancelar() {
        finish()
    }

    private fun authenticate(myuser: EditText, password: EditText): Boolean {
        if (myuser.text.isNotEmpty() && myuser.text.isNotBlank() && password.text.isNotBlank() && password.text.isNotEmpty()) {
            return myuser.text.toString() == "u" && password.text.toString() == "12345"
        }
        return false
    }

    private fun ingresar() {

        if (authenticate(myuser, password)){
            //Guardar el estado de la sesion
            saveLoginData(myuser, password,checkBox.isChecked)
            saveUserSession(myuser.toString())
            startMainActivity()
        }else{
            Toast.makeText(this, "Correo o contraseña incorrectas", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveUserSession(myuser: String) {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("myuser", myuser)
        editor.apply()

    }

    private fun saveLoginData(myuser: EditText, password: EditText, checked: Boolean) {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val rememberMe = sharedPreferences.getBoolean("rememberMe", false)

        if (rememberMe){
            val myuser = sharedPreferences.getString("myuser", "") ?: ""
            val mypassword = sharedPreferences.getString("mypassword", "") ?: ""

            this.myuser.setText(myuser)
            this.password.setText(mypassword)
            checkBox.isChecked = true
        }
    }

    fun checkUserSession() {
        val myuser = getUserSession()

        if (myuser.isNotEmpty()) {
            startMainActivity()
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this, IngresoActivity::class.java)
        startActivity(intent)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun getUserSession(): String {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        return sharedPreferences.getString("myuser", "") ?: ""
    }

}