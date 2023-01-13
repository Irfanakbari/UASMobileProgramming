package com.example.utsmobileprogramming.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.utsmobileprogramming.R
import com.example.utsmobileprogramming.utility.Utils

class CallItEvenFragment : Fragment() {
    private lateinit var buttons : Array<Button>
    private lateinit var views : View
    private lateinit var fragmentTransaction: FragmentTransaction
    private var arrayOptions = arrayListOf<Int>()
    var skor = 0
    private var soal = 0
    private var totalSkor = 0
    private var utils = Utils()

    override fun onDestroyView() {
        super.onDestroyView()
        utils.stopTimer()
    }

    private fun generateSoal() {
        arrayOptions.clear()
        var temp = (1..20).random()
//        Genap
        for (i in 0..3){
            while (temp % 2 != 0) {
                temp = (1..20).random()
            }
            while (arrayOptions.contains(temp)) {
                temp = (1..20).random()
                while (temp % 2 != 0) {
                    temp = (1..20).random()
                }
            }
            arrayOptions.add(temp)
        }
//        Ganjil
        for (i in 0..4) {
            temp = (1..20).random()
            while (temp % 2 == 0) {
                temp = (1..20).random()
            }
            while (arrayOptions.contains(temp)) {
                temp = (1..20).random()
                while (temp % 2 == 0) {
                    temp = (1..20).random()
                }
            }
            arrayOptions.add(temp)
        }
//        acak array
        arrayOptions.shuffle()
        generateButtonText()
    }

    private fun generateButtonText() {
        for (button in buttons) {
            button.isEnabled = true
            button.setBackgroundColor(resources.getColor(R.color.cie_button))
            button.text = arrayOptions[buttons.indexOf(button)].toString()
        }
    }


    private fun checkButton(index: Int) {
        if (arrayOptions[index] % 2 == 0) {
            skor += 1
            buttons[index].text = arrayOptions[index].toString()
            buttons[index].setBackgroundColor(resources.getColor(R.color.cie_button_pressed))
            buttons[index].isEnabled = false
        }
        else {
            views.findViewById<TextView>(R.id.skorCIE).setBackgroundColor(Color.RED)
            startGame()
        }
        if (skor == 4) {
            views.findViewById<TextView>(R.id.skorCIE).setBackgroundColor(Color.GREEN)
            totalSkor++
            startGame()
        }
    }

    private fun startGame(){
        utils.checkSoalNumber(soal, fragmentTransaction, totalSkor,"cie")
        for (button in buttons) {
            button.isEnabled = false
        }
        utils.delay(1300){
            utils.stopTimer()
            skor = 0
            soal++
            views.findViewById<TextView>(R.id.skorCIE).setBackgroundColor(Color.parseColor("#FFCDCD"))
            views.findViewById<TextView>(R.id.pembagi).setText(R.string.cie_ingat_genap)
            views.findViewById<TextView>(R.id.soalKe).text = resources.getString(R.string.soal_ke, soal.toString())
            views.findViewById<TextView>(R.id.skorCIE).text = resources.getString(R.string.skor, totalSkor.toString())
            generateSoal()
            utils.delay(2000){
                for (button in buttons) {
                    button.text = ""
                }
                views.findViewById<TextView>(R.id.pembagi).setText(R.string.cie_angka_genap_dimana)
                utils.startTimer()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        views = inflater.inflate(R.layout.fragment_call_it_even, container, false)
        utils.timerHandler(views.findViewById(R.id.progressBar), 8000, ::startGame)
        fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()!!
        buttons = arrayOf(
            views.findViewById(R.id.optionA),
            views.findViewById(R.id.optionB),
            views.findViewById(R.id.optionC),
            views.findViewById(R.id.optionD),
            views.findViewById(R.id.optionE),
            views.findViewById(R.id.optionF),
            views.findViewById(R.id.optionG),
            views.findViewById(R.id.optionH),
            views.findViewById(R.id.optionI)
        )
        for (button in buttons) {
            button.setOnClickListener {
                checkButton(buttons.indexOf(button))
            }
        }
        views.findViewById<ImageButton>(R.id.imageButton).setOnClickListener {
            activity?.onBackPressed()
        }
        startGame()
        return views
    }
}