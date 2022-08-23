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

class Adapter_profileComplain(
    var context: Context,
    var profilecomplainlist: ArrayList<Model_usercomplain>,
    var btnclicks: btnclicked
) : RecyclerView.Adapter<Adapter_profileComplain.ViewProfilecomplainHolder>() {


    interface btnclicked {
        fun onSpeakerClicked(position: Int, complains: String)
        fun onEditClicked(position: Int, id: Int, complains: String, name: String, flatno: String)
        fun onDeleteClicked(position: Int, id: Int, complains: String, name: String, flatno: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewProfilecomplainHolder {
        var v = LayoutInflater.from(context)
            .inflate(R.layout.layout_profile_complainlist, parent, false)
        return Adapter_profileComplain.ViewProfilecomplainHolder(v)
    }

    override fun onBindViewHolder(holder: ViewProfilecomplainHolder, position: Int) {
        var mymodel = profilecomplainlist[position]

        holder.tvname.text = mymodel.name.toString()
        holder.flatno.text = mymodel.flatno.toString()
        holder.complain.text = mymodel.complains.toString()

        holder.iv_speaker.setOnClickListener {
            btnclicks.onSpeakerClicked(position, mymodel.complains)
        }

        holder.iv_Edit.setOnClickListener {
            btnclicks.onEditClicked(
                position,
                mymodel.id,
                mymodel.complains,
                mymodel.name,
                mymodel.flatno
            )
        }

        holder.iv_delete.setOnClickListener {
            btnclicks.onDeleteClicked(
                position,
                mymodel.id,
                mymodel.complains,
                mymodel.name,
                mymodel.flatno
            )

        }

    }

    override fun getItemCount(): Int {
        return profilecomplainlist.size
    }

    class ViewProfilecomplainHolder(item: View) : RecyclerView.ViewHolder(item) {
        var tvname = item.findViewById<TextView>(R.id.tv_name)
        var flatno = item.findViewById<TextView>(R.id.tv_flatno)
        var complain = item.findViewById<TextView>(R.id.tv_complain)
        var iv_speaker = item.findViewById<ImageView>(R.id.iv_speaker)
        var iv_Edit = item.findViewById<ImageView>(R.id.profile_edit)
        var iv_delete = item.findViewById<ImageView>(R.id.profile_delete)
    }
}