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
        tv_num1 = findViewById(R.id.tv_num1)
        tv_num2 = findViewById(R.id.tv_num2)

        btnIgual.setOnClickListener {
            val num2 = tv_num2.text.toString().toDoubleOrNull() ?: 0.0
            var res = 0.0
            when (oper) {
                1 -> res = numero1 + num2
                2 -> res = numero1 - num2
                3 -> res = numero1 * num2
                4 -> res = numero1 / num2
            }
            tv_num2.setText(res.toString())
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
    }

    fun clicOperacion(view: View) {
        val num2 = tv_num2.text.toString()
        if (num2.isNotEmpty()) {
            numero1 = num2.toDouble()
        }
        when (view.id) {
            R.id.btnSumar -> {
                if (num2.isNotEmpty()) {
                    numero1 = num2.toDouble()
                    tv_num2.setText("")
                }
                tv_num1.setText(num2 + "+")
                oper = 1
                isNum2Selected = true
            }
            R.id.btnRestar -> {
                if (num2.isEmpty()) {
                    tv_num2.setText("-")
                } else {
                    numero1 = num2.toDouble()
                    tv_num2.setText("")
                    tv_num1.setText(num2 + "-")
                }
                oper = 2
                isNum2Selected = true
            }
            R.id.btnMult -> {
                if (num2.isNotEmpty()) {
                    numero1 = num2.toDouble()
                    tv_num2.setText("")
                }
                tv_num1.setText(num2 + "*")
                oper = 3
                isNum2Selected = true
            }
            R.id.btnDividir -> {
                if (num2.isNotEmpty()) {
                    numero1 = num2.toDouble()
                    tv_num2.setText("")
                }
                tv_num1.setText(num2 + "/")
                oper = 4
                isNum2Selected = true
            }
        }
    }

    // Llamado cuando se hace clic en el botón de borrar
    fun clicBorrar(view: View) {
        if (isNum2Selected) {
            if (tv_num2.text.isNotEmpty()) {
                tv_num2.text = tv_num2.text.toString().dropLast(1)
            }
            if (tv_num2.text.isEmpty()) {
                tv_num2.text = ""
            }
        } else {
            if (tv_num1.text.isNotEmpty()) {
                tv_num1.text = tv_num1.text.toString().dropLast(1)
            }
            if (tv_num1.text.isEmpty()) {
                tv_num1.text = ""
            }
        }
    }
}
