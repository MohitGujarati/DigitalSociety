package mohit.dev.digitalsoc.code.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mohit.dev.digitalsoc.R
import org.w3c.dom.Text

class Frag_Profile_card : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag__profile_card, container, false)

        var tvusername=view.findViewById<TextView>(R.id.profile_ownersname)
        var tvprofile_flatno=view.findViewById<TextView>(R.id.profile_flatno)
        var tvprofile_tvposition=view.findViewById<TextView>(R.id.profile_tvposition)
        var tvprofile_user_position=view.findViewById<TextView>(R.id.profile_users_position)

        val bundle = arguments
        val username = bundle!!.getString("compuser")
        val flatno = bundle.getString("compflatno")
        val position = bundle.getString("compposition")

        tvusername.text=username.toString()
        tvprofile_flatno.text=flatno.toString()
        tvprofile_user_position.text=position.toString()

        return view
    }

}