package com.example.utsmobileprogramming.fragments

import SkorAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utsmobileprogramming.R
import com.example.utsmobileprogramming.model.LeaderBoard
import com.example.utsmobileprogramming.utility.FirebaseService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LeaderBoardFragment : Fragment() {

    private lateinit var rvMain: RecyclerView
    private var listHeroes : MutableList<LeaderBoard> = emptyList<LeaderBoard>().toMutableList()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            val querySnapshot = FirebaseService.getAllData().await()
            for (document in querySnapshot.documents) {
                listHeroes.add(
                    LeaderBoard(
                        uid = document.id,
                        time = document.data?.get("time"),
                        skor = document.data?.get("skor")
                    )
                )
            }
            activity?.runOnUiThread {
                val heroesAdapter = SkorAdapter(listHeroes)
                rvMain.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = heroesAdapter
                }
                // use the querySnapshot
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val views = inflater.inflate(R.layout.fragment_leader_board, container, false)
        rvMain = views.findViewById(R.id.leaderbord)
        return views
    }
}