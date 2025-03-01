package com.sugardaddy.multitask

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Promedio : AppCompatActivity() {

    private lateinit var spnDesplegable: Spinner
    private lateinit var edtNumero: EditText
    private lateinit var btnGuardar: Button
    private lateinit var edtNombre:EditText
    private lateinit var btnResultado:Button
    private lateinit var tvResultado:TextView

    private val valoresGuardados = mutableMapOf<String, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_promedio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtNombre= findViewById(R.id.edtNombre)
        spnDesplegable = findViewById(R.id.desplegable)
        edtNumero = findViewById(R.id.edtNota)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnResultado = findViewById(R.id.btnResultado)
        tvResultado= findViewById(R.id.tvResultado)

        //Lista de elementos
        val opciones = listOf("Nota 1","Nota 2","Nota 3","Nota 4","Nota 5")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,opciones)
        spnDesplegable.adapter=adapter

        spnDesplegable.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem= opciones[position]
                edtNumero.setText(valoresGuardados[selectedItem]?.toString() ?: "")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        //Guardar el valor ingresado cuando se preciona el botón
        btnGuardar.setOnClickListener{
            val selectedItem = spnDesplegable.selectedItem.toString()
            val inputValue = edtNumero.text.toString().toIntOrNull()

            if(inputValue != null){
                if(inputValue >= 0 && inputValue <= 10){
                    valoresGuardados[selectedItem] = inputValue
                    Toast.makeText(this,"Valor guardado para $selectedItem",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Ingrese un número entre el 0 y 10",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Ingrese un número válido",Toast.LENGTH_SHORT).show()
            }
        }
        // Mostrar notas y calcular el promedio

        btnResultado.setOnClickListener{
            val nombreEstudiante = edtNombre.text.toString().trim()
            if(valoresGuardados.size == 5){
                if(nombreEstudiante.isNotEmpty()){
                    var notasTexto=""
                    var sumaNotas=0
                    var cantidadNotas=5

                    //Recorrer el mapa manualmente
                    for((nombre,nota) in valoresGuardados){
                        notasTexto += "$nombre: $nota\n"
                        sumaNotas += nota
                    }
                    //promedio
                    val promedio = sumaNotas.toDouble() / cantidadNotas
                    val promedioFormateado = String.format("%.2f",promedio)

                    tvResultado.text="${edtNombre}\n Calificaciones: $notasTexto\n Promedio: $promedioFormateado"
                }else{
                    Toast.makeText(this,"Ingrese el nombre del estudiante",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Debe ingresar las 5 notas antes de calcular el promedio", Toast.LENGTH_SHORT).show()
            }

        }
        val btnregresar = findViewById<ImageButton>(R.id.btnBack)
        btnregresar.setOnClickListener{
            finish()
        }


    }
}