package com.kaptarr.calculator


import com.kaptarr.calculator.CalculatorFragment.Companion.ADD
import com.kaptarr.calculator.CalculatorFragment.Companion.DECIMAL
import com.kaptarr.calculator.CalculatorFragment.Companion.DIVIDE
import com.kaptarr.calculator.CalculatorFragment.Companion.MULTIPLY_LOGIC
import com.kaptarr.calculator.CalculatorFragment.Companion.MULTIPLY_SYMBOL
import com.kaptarr.calculator.CalculatorFragment.Companion.PERCENTAGE
import com.kaptarr.calculator.CalculatorFragment.Companion.SUBTRACT
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat

private var operationString: String = ""
private var operationScreen: String = ""

class Operation {

    fun append(text: String) {
        val symbol = when (text) {
            MULTIPLY_LOGIC -> MULTIPLY_SYMBOL
            else -> text
        }
        operationScreen += symbol
        operationString += text
    }

    fun delete() {
        if (operationString.isNotEmpty()) {
            operationString = operationString.dropLast(1)
            operationScreen = operationScreen.dropLast(1)
        }
    }

    fun clear() {
        operationString = ""
        operationScreen = ""
    }

    fun evaluate(): Double {
        val result: Double
        if (operationString.isNotEmpty()) {

            val expression = ExpressionBuilder(operationString).build()
            result = expression.evaluate()
        } else {
            result = 0.0
        }
        return result
    }

    fun formatResult(result: Double): String{
        return DecimalFormat("#.##").format(result)
    }

    fun evaluatePercentage(): Double {
        val result = operationString.split(PERCENTAGE)
        if (result.size == 2) {
            return (result[0].toDouble() * result[1].toDouble() / 100)
        } else{
            return 0.0
        }

    }

    fun isValidExpression(expresion: String): Boolean {
        val operadores = listOf(ADD, SUBTRACT, MULTIPLY_LOGIC, DIVIDE, DECIMAL, PERCENTAGE)
        return expresion.isNotEmpty() && !operadores.contains(
            expresion.last().toString()
        ) && !operadores.contains(expresion.first().toString())
    }

        fun getOperationScreen(): String = operationScreen
        fun getOperationString(): String = operationString


    }