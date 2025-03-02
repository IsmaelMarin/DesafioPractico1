package com.sugardaddy.multitask

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Descuento : AppCompatActivity() {

    private lateinit var edtNombre:EditText
    private lateinit var edtSalario:EditText
    private lateinit var tvResultado:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_descuento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Inicializamos variables
        edtNombre=findViewById(R.id.edtNombre)
        edtSalario=findViewById(R.id.edtSalario)
        tvResultado=findViewById(R.id.tvResultado)

        val btnCalcular=findViewById<Button>(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            salarioNeto()
        }

        val regresar=findViewById<ImageButton>(R.id.btnBack)
        regresar.setOnClickListener{
            finish()
        }
    }
    private fun salarioNeto(){
        val salario=edtSalario.text.toString().toDoubleOrNull()
        val nombre=edtNombre.text.toString()

        if(nombre.isNullOrEmpty()){
            tvResultado.text="Ingrese un nombre valido"
            return
        }
        if(salario == null){
            tvResultado.text="Ingrese un salario, por favor"
            return
        }

        val afp=salario*(0.075)
        val isss=salario*(0.03)
        var renta=0.0
        when {
            salario in 0.01..472.0 -> renta = 0.0
            salario in 472.01..895.24 -> renta = (salario - 472.0) * 0.1 + 17.67
            salario in 895.25..2038.10 -> renta = (salario - 895.24) * 0.2 + 60.0
            salario > 2038.11 -> renta = (salario - 2038.10) * 0.3 + 288.57
        }

        val salarioNeto = salario - (afp + isss + renta)

        // Mostramos los resultados en el TextView
        tvResultado.text = """
            Nombre: $nombre
            Salario Base: $$salario
            AFP (7.25%): $$afp
            ISSS (3%): $$isss
            Renta: $$renta
            Salario Neto: $$salarioNeto
        """.trimIndent()
    }

}