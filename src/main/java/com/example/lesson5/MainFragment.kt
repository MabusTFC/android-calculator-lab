package com.example.lesson5

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.lesson5.databinding.FragmentMainBinding


class MainFragment: Fragment(), View.OnClickListener {

    private val actions = listOf("x", "+", "÷", "-")
    private val binding: FragmentMainBinding by lazy { FragmentMainBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.keyboard.children
            .filterIsInstance<Button>()
            .forEach { it.setOnClickListener(this) }
    }

    override fun onClick(view: View?) {
        val button = view as? Button
        button ?: return
        val buttonValue = button.text.toString()
        when {

            buttonValue.first().isDigit() -> {
                binding.mainText.text = binding.mainText.text.toString() + buttonValue
            }

            actions.contains(buttonValue) -> {
                if (actions.contains(binding.mainText.text.last().toString())) {
                    return
                } else {
                    binding.mainText.text = binding.mainText.text.toString() + buttonValue
                }
            }

            buttonValue == "," -> {
                val str = binding.mainText.text.toString()
                if (str.length == 0 || actions.contains(str.last().toString())) {
                    binding.mainText.text = binding.mainText.text.toString() + "0"
                }
                for (i in str.reversed()) {
                    if (!actions.contains(i.toString())) {
                        if (i.toString() == ",") {
                            return
                        }
                    } else {
                        break
                    }
                }
                binding.mainText.text = binding.mainText.text.toString() + buttonValue
            }

            buttonValue == "AC" -> {
                binding.mainText.text = ""
                binding.topText.text = ""
            }

            buttonValue == "=" -> {
                binding.topText.text = binding.mainText.text
                binding.mainText.text = evaluateExpression(binding.topText.text.toString())
            }


            else -> {
                val toast: Toast =
                    Toast.makeText(requireContext(), "Ещё не сделал", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 0, 160)
                toast.show()
            }
        }
    }

    fun evaluateExpression(expression: String): String {
        val cleanExpr = expression.replace(",", ".")
            .replace("x", "*")
            .replace("÷", "/")

        val parts = cleanExpr.split("+", "-", "*", "/")
        val numbers = parts.map { it.toDouble() }
        val operators = cleanExpr.filter { it in "+-*/" }

        if (numbers.isEmpty()) return "0"

        var result = numbers[0]

        for (i in 1 until numbers.size) {
            when (operators[i - 1]) {
                '+' -> result += numbers[i]
                '-' -> result -= numbers[i]
                '*' -> result *= numbers[i]
                '/' -> {
                    if (numbers[i] != 0.0) {
                        result /= numbers[i]
                    } else {
                        return "Error"
                    }
                }
            }
        }

        if (result % 1 == 0.0) {
            return result.toInt().toString()
        } else {
            return result.toString().replace(".", ",")
        }

    }

}
