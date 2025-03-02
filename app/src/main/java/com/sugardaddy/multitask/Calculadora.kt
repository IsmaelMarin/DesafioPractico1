package com.sugardaddy.multitask

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow
import kotlin.math.sqrt

class Calculadora : AppCompatActivity() {

    private lateinit var edta:EditText
    private lateinit var edtb:EditText
    private lateinit var tvOperacion:TextView
    private lateinit var tvRespuesta:TextView
    private lateinit var tvRespuesta2:TextView
    private lateinit var edtRaiz:EditText
    private var operador:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Inicializacomo elementos
        edta=findViewById(R.id.edtA)
        edtb=findViewById(R.id.edtB)
        tvOperacion=findViewById(R.id.tvOperador)
        tvRespuesta=findViewById(R.id.tvRespuesta)
        tvRespuesta2=findViewById(R.id.tvRespuesta2)
        edtRaiz=findViewById(R.id.edtRaiz)


        val btnSumar:Button=findViewById(R.id.btnSumar)
        val btnRestar:Button=findViewById(R.id.btnRestar)
        val btnMultiplicar:Button=findViewById(R.id.btnMultiplicar)
        val btnDividir:Button=findViewById(R.id.btnDividir)
        val btnExponente:Button=findViewById(R.id.btnPotencia)
        val ibLimpiar: ImageButton =findViewById(R.id.ibLimpiar)
        val btnCalcular:Button=findViewById(R.id.btnOperacion)
        val btnCalcular2:Button=findViewById(R.id.btnOperacion2)
        val btnReturn:ImageButton=findViewById(R.id.btnReturn)

        btnSumar.setOnClickListener{ setOperacion("+") }
        btnRestar.setOnClickListener{ setOperacion("-") }
        btnMultiplicar.setOnClickListener{ setOperacion("x") }
        btnDividir.setOnClickListener{ setOperacion("/") }
        btnExponente.setOnClickListener{ setOperacion("^") }
        ibLimpiar.setOnClickListener{Limpiar()}
        btnCalcular2.setOnClickListener{calcularRaiz()}
        btnCalcular.setOnClickListener{calcularResultado()}

        btnReturn.setOnClickListener{
            finish()
        }

    }
    private fun setOperacion(op:String){
        operador=op
        tvOperacion.text=operador
    }
    private fun calcularResultado(){
        val numeroA =edta.text.toString().toDoubleOrNull()
        val numeroB= edtb.text.toString().toDoubleOrNull()

        if(numeroA == null || numeroB == null){
            tvRespuesta.text="Ingrese valores validos"
            return
        }
        val resultado= when(operador){
            "+" -> numeroA + numeroB
            "-" -> numeroA - numeroB
            "x" -> numeroA * numeroB
            "/" -> if(numeroB != 0.0) numeroA / numeroB else "Error"
            "^" -> numeroA.pow(numeroB)
            else -> "Seleccione una operacion"
        }
        tvRespuesta.text=resultado.toString()
    }
    private fun calcularRaiz(){
        val numeroA= edtRaiz.text.toString().toDoubleOrNull()

        if(numeroA == null){
            tvRespuesta2.text="Ingrese valores validos"
            return
        }
        tvRespuesta2.text= if(numeroA >= 0) sqrt(numeroA).toString() else "Error: número negativo"
    }
    private fun Limpiar(){
        tvRespuesta.text=""
        tvRespuesta2.text=""
        tvOperacion.text="."
        edta.setText("")
        edtb.setText("")
        edtRaiz.setText("")
    }




}