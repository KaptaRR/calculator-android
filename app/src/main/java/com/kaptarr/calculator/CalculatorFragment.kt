package com.kaptarr.calculator

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaptarr.calculator.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {

    companion object {
        const val ZERO = "0"
        const val ONE = "1"
        const val TWO = "2"
        const val THREE = "3"
        const val FOUR = "4"
        const val FIVE = "5"
        const val SIX = "6"
        const val SEVEN = "7"
        const val EIGHT = "8"
        const val NINE = "9"


        const val ADD = "+"
        const val SUBTRACT = "-"
        const val MULTIPLY_SYMBOL = "x"
        const val MULTIPLY_LOGIC = "*"
        const val DIVIDE = "/"
        const val DECIMAL = "."
        const val PERCENTAGE = "%"
    }

    private lateinit var binding: FragmentCalculatorBinding
    var operation: Operation = Operation()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }


    private fun updateUI() {
        binding.screenOperation.text = operation.getOperationScreen()
    }

    private fun onButtonClick(action: () -> Unit) {
        action()
        updateUI()
    }


    private fun initListener() {
        // Delete
        binding.btnAC.setOnClickListener {
            onButtonClick { operation.clear() }
            binding.screenResult.text = ""
        }
        binding.btnDelete.setOnClickListener { onButtonClick { operation.delete() } }

        // Numbers
        binding.btnZero.setOnClickListener { onButtonClick { operation.append(ZERO) } }
        binding.btnOne.setOnClickListener { onButtonClick { operation.append(ONE) } }
        binding.btnTwo.setOnClickListener { onButtonClick { operation.append(TWO) } }
        binding.btnThree.setOnClickListener { onButtonClick { operation.append(THREE) } }
        binding.btnFour.setOnClickListener { onButtonClick { operation.append(FOUR) } }
        binding.btnFive.setOnClickListener { onButtonClick { operation.append(FIVE) } }
        binding.btnSix.setOnClickListener { onButtonClick { operation.append(SIX) } }
        binding.btnSeven.setOnClickListener { onButtonClick { operation.append(SEVEN) } }
        binding.btnEight.setOnClickListener { onButtonClick { operation.append(EIGHT) } }
        binding.btnNine.setOnClickListener { onButtonClick { operation.append(NINE) } }

        // Operators
        binding.btnPlus.setOnClickListener { onButtonClick { operation.append(ADD) } }
        binding.btnMinus.setOnClickListener { onButtonClick { operation.append(SUBTRACT) } }
        binding.btnMultiply.setOnClickListener { onButtonClick { operation.append(MULTIPLY_LOGIC) } }
        binding.btnDivide.setOnClickListener { onButtonClick { operation.append(DIVIDE) } }
        binding.btnPercentatge.setOnClickListener { onButtonClick { operation.append(PERCENTAGE) } }

        // Decimal
        binding.btnDecimal.setOnClickListener { onButtonClick { operation.append(DECIMAL) } }

        // Evaluate
        binding.btnEqual.setOnClickListener {
            val operationString = operation.getOperationString()

            if (operation.isValidExpression(operationString)) {
                binding.screenResult.text = if (operationString.contains(PERCENTAGE)) {
                    operation.formatResult(operation.evaluatePercentage())
                } else {
                    operation.formatResult(operation.evaluate())
                }
            }
        }

    }


}