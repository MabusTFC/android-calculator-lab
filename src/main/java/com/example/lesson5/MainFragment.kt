package com.example.lesson5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.lesson5.databinding.ActivityMainBinding
import com.example.lesson5.databinding.FragmentMainBinding
import dev.androidbroadcast.vbpd.viewBinding


class MainFragment: Fragment(), View.OnClickListener {

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




    }

    override fun onClick(v: View?) {
        val button = view as? Button
        button ?: return
        val buttonValue = button.text.toString()
        when{
            buttonValue.first().isDigit() || buttonValue== "+"-> {


            }
        }


    }


}