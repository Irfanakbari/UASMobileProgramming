import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.utsmobileprogramming.R
import com.example.utsmobileprogramming.model.LeaderBoard

class SkorAdapter(private val heroes: List<LeaderBoard>) : RecyclerView.Adapter<SkorHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): SkorHolder {
        return SkorHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.leaderboard_item, viewGroup, false))
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: SkorHolder, position: Int) {
        holder.bindHero(heroes[position])
    }
}

class SkorHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvHeroName = view.findViewById<TextView>(R.id.name_ld)
    private val skorLd = view.findViewById<TextView>(R.id.skor_ld)

    fun bindHero(hero: LeaderBoard) {
        tvHeroName.text = hero.uid
        skorLd.text = hero.skor.toString()
    }
}