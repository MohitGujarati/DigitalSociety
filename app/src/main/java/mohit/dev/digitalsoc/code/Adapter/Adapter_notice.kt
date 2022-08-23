package mohit.dev.digitalsoc.code.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mohit.dev.digitalsoc.R
import mohit.dev.digitalsoc.code.Model.Model_noticedb


class Adapter_notice(
    var context: Context,
    var complainlist: ArrayList<Model_noticedb>,
    var speakerclicked: SpeakerClicked
) : RecyclerView.Adapter<Adapter_notice.Complainholder>() {


    interface SpeakerClicked {
        fun onSpeakerClicked(position: Int, complains: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Complainholder {
        var v = LayoutInflater.from(context).inflate(R.layout.layout_notice_list, parent, false)
        return Complainholder(v)
    }

    override fun onBindViewHolder(holder: Complainholder, position: Int) {
        var mymodel = complainlist[position]

        holder.notice_title.text = mymodel.title.toString()
        holder.notice_date.text = mymodel.date.toString()
        holder.notice.text = mymodel.notice.toString()

        holder.notice_speaker.setOnClickListener {
            speakerclicked.onSpeakerClicked(position,mymodel.notice)
        }

    }


    override fun getItemCount(): Int {
        return complainlist.size
    }

    class Complainholder(item: View) : RecyclerView.ViewHolder(item) {

        var notice_title = item.findViewById<TextView>(R.id.notice_title)
        var notice_date = item.findViewById<TextView>(R.id.notice_date)
        var notice = item.findViewById<TextView>(R.id.notice_notice)
        var notice_speaker = item.findViewById<ImageView>(R.id.notice_speaker)

    }
}

