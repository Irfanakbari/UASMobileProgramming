package com.example.utsmobileprogramming.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.utsmobileprogramming.R
import com.example.utsmobileprogramming.utility.Utils


class OperationMathFragment : Fragment() {
    private lateinit var buttons : Array<Button>
    private lateinit var views : View
    private lateinit var fragmentTransaction : FragmentTransaction
    private var penjumlahan = arrayListOf<Int>()
    private var pengurangan = arrayListOf<Int>()
    private var hasil = arrayListOf<Int>()
    private var soal = 0
    private var totalSkor = 0
    private var utils = Utils()

    override fun onDestroyView() {
        super.onDestroyView()
        utils.stopTimer()
    }

    private fun generateSoal(){
        pengurangan.clear()
        penjumlahan.clear()
        hasil.clear()
//        variabel
        var temp = (1..20).random()
        var temp1 = (1..50).random()
        var temp2 = (1..50).random()
//        Penjumlahan
        for (i in 0..3){
            while (penjumlahan.contains(temp)) {
                temp = (1..20).random()
            }
            penjumlahan.add(temp)
        }
//        Pengurangan
        while (temp1 < temp2 || temp1 == temp2) {
            temp1 = (1..50).random()
            temp2 = (1..50).random()
        }
        pengurangan.add(temp1)
        pengurangan.add(temp2)

//        hasil
        hasil.add(penjumlahan[0]+penjumlahan[1])
        hasil.add(penjumlahan[2]+penjumlahan[3])
        hasil.add(pengurangan[0]-pengurangan[1])

    }

    private fun generateButtonText(){
        buttons[0].text =resources.getString(R.string.penjumlahan, penjumlahan[0].toString(), penjumlahan[1].toString())
        buttons[1].text =resources.getString(R.string.penjumlahan, penjumlahan[2].toString(), penjumlahan[3].toString())
        buttons[2].text =resources.getString(R.string.pengurangan, pengurangan[0].toString(), pengurangan[1].toString())

    }

    private fun checkButton(index: Int) {

        var largest = hasil[0]

        for (num in hasil) {
            if (largest < num)
                largest = num
        }

        if (hasil[index] == largest) {
            totalSkor++
            utils.addTime()
            startGame()
        } else {

            startGame()
        }
    }

    private fun endGame(){
        val bundle = Bundle()
        val skorFragment = SkorFragment()
        bundle.putInt("skor", totalSkor)
        bundle.putString("thisFragment", "operationMath")
        skorFragment.arguments = bundle
        fragmentTransaction.replace(R.id.gameContainer, skorFragment)
        fragmentTransaction.commit()
    }

    private fun startGame() {

        soal++
        views.findViewById<TextView>(R.id.soalKe).text = resources.getString(R.string.soal_ke, soal.toString())
        views.findViewById<TextView>(R.id.skorCIE).text = resources.getString(R.string.skor, totalSkor.toString())
        generateSoal()
        generateButtonText()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        views = inflater.inflate(R.layout.fragment_operation_math, container, false)
        utils.timerHandler(views.findViewById(R.id.progressBar), 10000, ::endGame)
        utils.startTimer()
        fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()!!
        buttons = arrayOf(
                views.findViewById(R.id.tombolA),
                views.findViewById(R.id.tombolB),
                views.findViewById(R.id.tombolC),
            )
        for (button in buttons) {
            button.setOnClickListener {
                checkButton(buttons.indexOf(button))
            }
        }
        startGame()
        views.findViewById<ImageButton>(R.id.imageButton).setOnClickListener {
            activity?.onBackPressed()
        }
        return views
    }

}