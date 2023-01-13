package com.example.utsmobileprogramming.utility

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ProgressBar
import androidx.fragment.app.FragmentTransaction
import com.example.utsmobileprogramming.R
import com.example.utsmobileprogramming.fragments.SkorFragment

class Utils {
    private lateinit var timer: CountDownTimer
    private lateinit var delay : CountDownTimer
    private var current = 0
    private lateinit var restart: ()->Unit
    private lateinit var progress: ProgressBar
    fun timerHandler(progressBar: ProgressBar,milis: Long ,restartFunction: () -> Unit) {
        restart = restartFunction
        progress = progressBar
        timer = object : CountDownTimer(milis, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                progressBar.progress = (millisUntilFinished / 1000).toInt()
                current = (millisUntilFinished / 1000).toInt()
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

    fun addTime() {
        val newTime = current + 3
        progress.max = newTime
        Log.d("Time", newTime.toString())
        timer.cancel()
        timer = object : CountDownTimer((newTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                progress.progress = (millisUntilFinished / 1000).toInt()
                current = (millisUntilFinished / 1000).toInt()
            }
            override fun onFinish() {
                stopTimer()
                restart()
            }
        }
        startTimer()
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