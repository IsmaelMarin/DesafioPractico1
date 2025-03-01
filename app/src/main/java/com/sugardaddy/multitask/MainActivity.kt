package com.sugardaddy.multitask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCalculadora = findViewById<Button>(R.id.btnCalculadora)
        btnCalculadora.setOnClickListener {
            val pantalla = Intent(this, Calculadora::class.java)
            startActivity(pantalla)
        }

        val btnDescuento = findViewById<Button>(R.id.btnDescuento)
        btnDescuento.setOnClickListener {
            val pantalla = Intent(this, Descuento::class.java)
            startActivity(pantalla)
        }

        val btnPromedio = findViewById<Button>(R.id.btnPromedio)
        btnPromedio.setOnClickListener {
            val pantalla = Intent(this, Promedio::class.java)
            startActivity(pantalla)
        }
    }
}