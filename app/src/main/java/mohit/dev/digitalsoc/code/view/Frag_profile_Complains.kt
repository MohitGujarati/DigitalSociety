package mohit.dev.digitalsoc.code.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mohit.dev.digitalsoc.R


class Frag_profile_Complains : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        var view=inflater.inflate(R.layout.fragment_frag_profile__complains, container, false)

        return view
    }


}