package com.ipam.calculadorap

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // 0->nada; 1->suma; 2->resta; 3->mult; 4->div
    var oper: Int = 0
    var numero1: Double = 0.0
    lateinit var tv_num1: TextView
    lateinit var tv_num2: TextView
    private var isNum2Selected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIgual: Button = findViewById(R.id.btnIgual)
        val btnBorrar: Button = findViewById(R.id.btnBorrar)
        val btnBorrar1: Button = findViewById(R.id.btnBorrar1)
        tv_num1 = findViewById(R.id.tv_num1)
        tv_num2 = findViewById(R.id.tv_num2)

        btnIgual.setOnClickListener {
            val num2Text = tv_num2.text.toString()
            val num2 = num2Text.toDoubleOrNull() ?: 0.0
            var res: Any = 0.0 // Cambiar el tipo a Any para poder asignar un String en caso de error
            when (oper) {
                1 -> res = numero1 + num2
                2 -> res = numero1 - num2
                3 -> res = numero1 * num2
                4 -> {
                    // Verificar si num2 es cero para evitar la división por cero
                    if (num2 == 0.0) {
                        res = "Math Error"
                    } else {
                        res = numero1 / num2
                    }
                }
            }
            // Verificar si res es un Double o un String para asignarlo correctamente a tv_num2
            if (res is Double) {
                if (num2Text.contains(".")) {
                    tv_num2.setText(res.toString())
                } else {
                    tv_num2.setText(res.toInt().toString())
                }
            } else if (res is String) {
                tv_num2.setText(res)
            }
            tv_num1.setText("")
            isNum2Selected = false
        }

        btnBorrar.setOnClickListener {
            tv_num1.setText("")
            tv_num2.setText("")
            oper = 0
            numero1 = 0.0
            isNum2Selected = false
        }

        btnBorrar1.setOnClickListener {
            clicBorrar(it)
        }
    }

    fun clicNumero(view: View) {
        var num2 = tv_num2.text.toString()

        when (view.id) {
            R.id.btn0 -> tv_num2.setText(num2 + "0")
            R.id.btn1 -> tv_num2.setText(num2 + "1")
            R.id.btn2 -> tv_num2.setText(num2 + "2")
            R.id.btn3 -> tv_num2.setText(num2 + "3")
            R.id.btn4 -> tv_num2.setText(num2 + "4")
            R.id.btn5 -> tv_num2.setText(num2 + "5")
            R.id.btn6 -> tv_num2.setText(num2 + "6")
            R.id.btn7 -> tv_num2.setText(num2 + "7")
            R.id.btn8 -> tv_num2.setText(num2 + "8")
            R.id.btn9 -> tv_num2.setText(num2 + "9")
            R.id.btnPunto -> tv_num2.setText(num2 + ".")
        }

        isNum2Selected = true
    }

    fun clicOperacion(view: View) {
        var num2 = tv_num2.text.toString()
        // Verificar si num2 es negativo o no
        val isNum2Negative = num2.startsWith("-")

        if (num2.isNotEmpty() && !isNum2Negative) {
            numero1 = num2.toDouble()
            tv_num2.setText("")
        } else if (isNum2Negative) {
            // Si num2 es negativo y estamos agregando una operación, necesitamos manejarlo adecuadamente
            if (num2.length > 1) { // Asegurarse de que hay más que solo el signo negativo
                numero1 = num2.toDouble()
                tv_num2.setText("")
            }
        }

        when (view.id) {
            R.id.btnSumar -> {
                tv_num1.setText(if (isNum2Negative && num2.length > 1) num2 + "+" else "+")
                oper = 1
            }
            R.id.btnRestar -> {
                if (num2.isEmpty() || (isNum2Negative && num2.length == 1)) {
                    // Si no hay número o solo hay un signo negativo, agregar otro signo negativo para permitir números negativos
                    tv_num2.setText("-")
                } else {
                    tv_num1.setText(if (isNum2Negative) num2 + "-" else "-")
                    oper = 2
                }
            }
            R.id.btnMult -> {
                tv_num1.setText(if (isNum2Negative && num2.length > 1) num2 + "*" else "*")
                oper = 3
            }
            R.id.btnDividir -> {
                tv_num1.setText(if (isNum2Negative && num2.length > 1) num2 + "/" else "/")
                oper = 4
            }
        }
        isNum2Selected = true
    }

    // Llamado cuando se hace clic en el botón de borrar
    fun clicBorrar(view: View) {
        val targetTextView = if (isNum2Selected) tv_num2 else tv_num1
        targetTextView.text = targetTextView.text.toString().dropLast(1)
    }
}