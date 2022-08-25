package mohit.dev.digitalsoc.code.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import mohit.dev.digitalsoc.R
import java.util.ArrayList

class Frag_Profile_card : Fragment() {

   lateinit var mytablayout: TabLayout
    var myviewpager: ViewPager? = null

    var fragmentEmailList: ArrayList<String> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag__profile_card, container, false)

        var tvusername=view.findViewById<TextView>(R.id.profile_ownersname)
        var tvprofile_flatno=view.findViewById<TextView>(R.id.profile_flatno)
        var tvprofile_tvposition=view.findViewById<TextView>(R.id.profile_tvposition)
        var profile_email=view.findViewById<TextView>(R.id.profile_email)
        var tvprofile_user_position=view.findViewById<TextView>(R.id.profile_users_position)


        mytablayout = view.findViewById(R.id.mytablayout)
        myviewpager =view.findViewById(R.id.myviewpager)


        val bundle = arguments
        val username = bundle!!.getString("baseact_to_Profile_username")
        val flatno = bundle.getString("baseact_to_Profile_flatno")
        val position = bundle.getString("baseact_to_Profile_position")
        val email = bundle.getString("baseact_to_Profile_email")

        tvusername.text=username.toString()
        tvprofile_flatno.text=flatno.toString()
        tvprofile_user_position.text=position.toString()
        profile_email.text=email.toString()

        Log.d("recived_data_Profilecard","${username} email:${email} position:${position}, flatno:${flatno}")





        loadtabs(mytablayout,email.toString(),profile_email,fragmentEmailList)

        /*
        emailfrg=profile_email.text.toString()
        Log.d("recived_data_tab","${emailfrg}")


        senddata(profile_email)

         */


        fragmentEmailList.add(profile_email.toString())

        Log.d("fragmentEmailList_card","${fragmentEmailList.toString()}")

        Log.d("ohh","${fragmentEmailList.toString()}")

        return view
    }



    private fun loadtabs(
        tab_layout: TabLayout,
        email: String,
        profile_email: TextView,
        fragmentEmailList: ArrayList<String>
    ) {
        if (tab_layout.isVisible) {
            Toast.makeText(context, "visible", Toast.LENGTH_SHORT).show()
            load_viewpager(email,profile_email)
        }
    }

    private fun load_viewpager(comp_email: String,profile_email: TextView) {

        mytablayout!!.tabGravity = TabLayout.GRAVITY_FILL

        setViewPager(myviewpager!!, comp_email,profile_email,fragmentEmailList)
        mytablayout!!.setupWithViewPager(myviewpager)


    }

    fun setViewPager(viewPager: ViewPager, comp_email: String,profile_email: TextView,fragmentEmailList: ArrayList<String>) {
        var adapter:ViewPagerAdapter =
            ViewPagerAdapter(parentFragmentManager)

        adapter.addFragment(Frag_profile_Complains(), "Complains",comp_email,fragmentEmailList)
        adapter.addFragment(Frag_profile_Notice(), "Notice",comp_email,fragmentEmailList)


        viewPager.adapter = adapter
    }


    class ViewPagerAdapter : FragmentPagerAdapter {
        var fragmentList: ArrayList<Fragment> = ArrayList()
        var fragmentTitleList: ArrayList<String> = ArrayList()





        constructor(supportFragmentManager: FragmentManager) : super(supportFragmentManager)




        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList.get(position)
        }

        override fun getPageTitle(position: Int): String {
            return fragmentTitleList.get(position)
        }



        fun addFragment(fragment: Fragment, title: String, comp_email: String,fragmentEmailList: ArrayList<String>) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)

        }


    }

    /*
    private fun senddata(profileEmail: TextView) {

        var Bundle = Bundle()
        var fragmentManager = parentFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragment = Frag_profile_Complains()

        if (profileEmail != null) {
            Bundle.putString("frg_emails", "${profileEmail.text.toString()}")
        }else{
            Bundle.putString("frg_emails", "1")
        }

        fragment.arguments = Bundle

        fragmentTransaction.add(R.id.invisible_profile, fragment).commit()

    }

     */


}