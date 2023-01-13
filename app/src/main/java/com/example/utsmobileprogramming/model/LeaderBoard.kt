package com.example.utsmobileprogramming.model

import com.google.firebase.Timestamp

data class LeaderBoard(
    val uid: String,
    val time: Timestamp?,
    val skor: Any?,
)
