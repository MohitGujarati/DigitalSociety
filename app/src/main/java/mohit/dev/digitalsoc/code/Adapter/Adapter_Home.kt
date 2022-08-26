package mohit.dev.digitalsoc.code.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import mohit.dev.digitalsoc.R
import mohit.dev.digitalsoc.code.Model.ModelClass_Home

class Adapter_Home(
    var context: Context,
    var eventlist: List<ModelClass_Home>,
    var cardClickedListener: CardClickedListener

) : RecyclerView.Adapter<Adapter_Home.ViewHolder>() {


    interface CardClickedListener {
        fun onCardClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.reclayout_home, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var mymodel = eventlist[position]
        holder.iv_image.setImageResource(mymodel.image)
        holder.tv_title.text = mymodel.title
        holder.tv_hint.text = mymodel.hint

        holder.container.setOnClickListener {
            cardClickedListener.onCardClicked(position)

        }
    }

    override fun getItemCount(): Int {
        return eventlist.size
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var iv_image = itemview.findViewById<ImageView>(R.id.iv_icons)
        var tv_title = itemview.findViewById<TextView>(R.id.tv_iconsname)
        var tv_hint = itemview.findViewById<TextView>(R.id.tv_hint)
        var container = itemview.findViewById<MaterialCardView>(R.id.container)


    }
}