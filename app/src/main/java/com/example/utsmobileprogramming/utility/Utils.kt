package com.example.utsmobileprogramming.utility

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import androidx.fragment.app.FragmentTransaction
import com.example.utsmobileprogramming.R
import com.example.utsmobileprogramming.fragments.SkorFragment

class Utils {
    private lateinit var timer: CountDownTimer
    private lateinit var delay : CountDownTimer

    fun timerHandler(progressBar: ProgressBar,milis: Long ,restartFunction: () -> Unit) {
        timer = object : CountDownTimer(milis, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                progressBar.progress = (millisUntilFinished / 1000).toInt()
            }
            override fun onFinish() {
                stopTimer()
                restartFunction()
            }
        }
    }

    fun startTimer() {
        timer.start()
    }

    fun stopTimer() {
        timer.cancel()
    }


    fun stopDelay() {
        delay.cancel()
    }

    fun checkSoalNumber(soal: Int, fragmentTransaction: FragmentTransaction, totalSkor: Int, typeGame: String) {
        if (soal>=10){
            val bundle = Bundle()
            val skorFragment = SkorFragment()
            bundle.putInt("skor", totalSkor)
            bundle.putString("thisFragment", typeGame)
            skorFragment.arguments = bundle
            fragmentTransaction.replace(R.id.gameContainer, skorFragment)
            fragmentTransaction.commit()
        }
    }

    fun delay(milis: Long, function: () -> Unit) {
        delay = object : CountDownTimer(milis, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                stopDelay()
                function()
            }
        }
        delay.start()
    }
}