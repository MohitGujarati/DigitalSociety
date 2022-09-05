package mohit.dev.digitalsoc.code.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mohit.dev.digitalsoc.R
import mohit.dev.digitalsoc.code.Model.Model_usercomplain


class Adapter_complain(
    var context: Context,
    var complainlist: ArrayList<Model_usercomplain>,
    var speakerclicked: SpeakerClicked
) : RecyclerView.Adapter<Adapter_complain.Complainholder>() {


    interface SpeakerClicked {
        fun onSpeakerClicked(position: Int, complains: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Complainholder {
        var v = LayoutInflater.from(context).inflate(R.layout.layout_complain_list, parent, false)
        return Complainholder(v)
    }

    override fun onBindViewHolder(holder: Complainholder, position: Int) {
        var mymodel = complainlist[position]

        holder.tvname.text = mymodel.name.toString()
        holder.flatno.text = mymodel.flatno.toString()
        holder.complain.text = mymodel.complains.toString()


        holder.iv_speaker.setOnClickListener {
            speakerclicked.onSpeakerClicked(position, mymodel.complains)
        }

    }


    override fun getItemCount(): Int {
        return complainlist.size
    }

    class Complainholder(item: View) : RecyclerView.ViewHolder(item) {

        var tvname = item.findViewById<TextView>(R.id.tv_name)
        var flatno = item.findViewById<TextView>(R.id.tv_flatno)
        var complain = item.findViewById<TextView>(R.id.tv_complain)
        var iv_speaker = item.findViewById<ImageView>(R.id.iv_speaker)

    }
}

