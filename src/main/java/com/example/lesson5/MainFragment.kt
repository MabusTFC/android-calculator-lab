package com.example.lesson5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

            actions.contains(buttonValue) ->{
                if (actions.contains(binding.mainText.text.last().toString())){
                    return
                }else{
                    binding.mainText.text = binding.mainText.text.toString() + buttonValue
                }
            }

            buttonValue == "," -> {
                val str = binding.mainText.text.toString()
                if(str.length == 0 || actions.contains(str.last().toString())){
                    binding.mainText.text = binding.mainText.text.toString() + "0"
                }
                for(i in str.reversed()) {
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

            buttonValue == "С" -> {
                binding.mainText.text = ""
            }


            else -> { /* And more more actions ;) */
            }
        }
    }
}
