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


class DivisorFragment : Fragment() {
    private lateinit var buttons : Array<Button>
    private lateinit var views : View
    private lateinit var fragmentTransaction : FragmentTransaction
    private var pembagi = 0
    private var arrayOption = arrayListOf<Int>()
    private var firstButtonPressed = false
    private var right = 0
    private var totalSkor = 0
    private var soal = 0
    private var utils = Utils()

    override fun onDestroyView() {
        super.onDestroyView()
        utils.stopTimer()
    }

    private fun generateSoal() {
        arrayOption.clear()
        var temp: Int
        do {
            temp = (2..6).random()
        } while (temp == 5 || temp == pembagi)
        pembagi = temp
        views.findViewById<TextView>(R.id.pembagi).text = pembagi.toString()

//        Generate Pilihan
        for (i in 0..1){
            do {
                temp = (7..50).random()
            } while (temp % pembagi == 0 || arrayOption.contains(temp))
            arrayOption.add(temp)
        }

//        Generate Jawaban
        for (i in 0..1){
            do {
                temp = (7..50).random()
            } while (temp % pembagi != 0 || arrayOption.contains(temp))
            arrayOption.add(temp)
        }
        arrayOption.shuffle()
    }

    private fun generateButtonText(){
        for (button in buttons) {
            button.setBackgroundColor(Color.parseColor("#760D2E"))
            button.text = arrayOption[buttons.indexOf(button)].toString()
        }
    }

    private fun checkButton(button: Button) {
        button.setBackgroundColor(Color.parseColor("#FFCCDC"))
        if (firstButtonPressed) {
            if (button.text.toString().toInt() % pembagi == 0) {
                right++
            } else {
//                views.findViewById<TextView>(R.id.skorCIE).setBackgroundColor(Color.RED)
                startGame()
            }
            firstButtonPressed = false
        } else {
            if (button.text.toString().toInt() % pembagi == 0) {
                right++
            } else {
//                views.findViewById<TextView>(R.id.skorCIE).setBackgroundColor(Color.RED)
                startGame()
            }
            firstButtonPressed = true
        }
        if (right == 2) {
//            views.findViewById<TextView>(R.id.skorCIE).setBackgroundColor(Color.GREEN)
            totalSkor++
            utils.addTime()
            startGame()
        }
    }

    private fun startGame(){
//        utils.checkSoalNumber(soal,  fragmentTransaction, totalSkor, "Divisor")
//        utils.delay(1500) {
//            views.findViewById<TextView>(R.id.skorCIE).setBackgroundColor(Color.parseColor("#FFCCDC"))
//        }
        soal++
        right = 0
        firstButtonPressed = false
        views.findViewById<TextView>(R.id.soalKe).text = resources.getString(R.string.soal_ke, soal.toString())
        views.findViewById<TextView>(R.id.skorCIE).text = resources.getString(R.string.skor, totalSkor.toString())
        generateSoal()
        generateButtonText()
    }

    private fun endGame(){
        val bundle = Bundle()
        val skorFragment = SkorFragment()
        bundle.putInt("skor", totalSkor)
        bundle.putString("thisFragment", "Divisor")
        skorFragment.arguments = bundle
        fragmentTransaction.replace(R.id.gameContainer, skorFragment)
        fragmentTransaction.commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        views = inflater.inflate(R.layout.fragment_divisor, container, false)
        fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()!!
        utils.timerHandler(views.findViewById(R.id.progressBar),10000, ::endGame)
        utils.startTimer()
        buttons = arrayOf(
            views.findViewById(R.id.buttonA),
            views.findViewById(R.id.buttonB),
            views.findViewById(R.id.buttonC),
            views.findViewById(R.id.buttonD)
        )
        startGame()
        for (button in buttons) {
            button.setOnClickListener {
                checkButton(it as Button)
            }
        }
        views.findViewById<ImageButton>(R.id.imageButton).setOnClickListener {
            activity?.onBackPressed()
        }
        return views
    }

}