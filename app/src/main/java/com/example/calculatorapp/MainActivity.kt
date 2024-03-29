package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorapp.ui.theme.CalculatorAppTheme
import java.lang.ArithmeticException

class MainActivity : ComponentActivity() {
    private var tvInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }




    fun onDigit(View: View){
        tvInput?.append((View as Button).text)
        lastNumeric = true
        lastDot = false
    }
    fun onClear(View: View){
        tvInput?.text = ""
    }
    fun onDecimalPoint(View: View){
        if (lastNumeric && !lastDot){
            tvInput?.append(".")
            lastDot = true
            lastNumeric = false
        }
    }
    fun onOperator(View: View){
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((View as Button).text)
                    lastDot = false
                    lastNumeric = false
            }
        }
    }

    fun onEqual(View: View){
        if (lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
        try {
            if (tvValue.startsWith("-")){
                prefix = "-"
                tvValue = tvValue.substring(1)

            }
            if (tvValue.contains("-")){

                val splitValue = tvValue.split("-")
                var one = splitValue[0] // e.g 99
                var two = splitValue[1] // e.g 1

                if (prefix.isNotEmpty()){
                    one = prefix + one
                }
                tvInput?.text =  removeZeroAfterDot((one.toDouble() - two. toDouble()).toString())
            }else if (tvValue.contains("+")){

                val splitValue = tvValue.split("+")
                var one = splitValue[0] // e.g 99
                var two = splitValue[1] // e.g 1

                if (prefix.isNotEmpty()){
                    one = prefix + one
                }
                tvInput?.text =  removeZeroAfterDot((one.toDouble() + two. toDouble()).toString())
            }else if (tvValue.contains("/")){

                val splitValue = tvValue.split("/")
                var one = splitValue[0] // e.g 99
                var two = splitValue[1] // e.g 1

                if (prefix.isNotEmpty()){
                    one = prefix + one
                }
                tvInput?.text = removeZeroAfterDot((one.toDouble() / two. toDouble()).toString())
            }else if (tvValue.contains("*")){

                val splitValue = tvValue.split("*")
                var one = splitValue[0] // e.g 99
                var two = splitValue[1] // e.g 1

                if (prefix.isNotEmpty()){
                    one = prefix + one
                }
                tvInput?.text = removeZeroAfterDot((one.toDouble() * two. toDouble()).toString())
            }

        }catch (e: ArithmeticException){
            e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String{
        var value = result
        if (result.contains("0"))
            value = result.substring(0, result.length -2)

        return value
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("-") || value.contains("+")
                    || value.contains("*")
        }
    }
}

