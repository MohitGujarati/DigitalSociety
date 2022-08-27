package mohit.dev.digitalsoc.code.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mohit.dev.digitalsoc.R

class Frag_Edit_complain : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var v=inflater.inflate(R.layout.fragment_frag__edit_complain, container, false)


        return v
    }


}