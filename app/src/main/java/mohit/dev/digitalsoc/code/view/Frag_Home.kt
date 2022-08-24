package mohit.dev.digitalsoc.code.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mohit.dev.digitalsoc.R
import mohit.dev.digitalsoc.code.Adapter.Adapter_Home
import mohit.dev.digitalsoc.code.Model.ModelClass_Home


class Frag_Home : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_frag__home, container, false)

        var recview = view.findViewById<RecyclerView>(R.id.rec_images)

        var tv_owners_name = view.findViewById<TextView>(R.id.tv_owners_name)
        var flat_no = view.findViewById<TextView>(R.id.flat_no)
        var users_position = view.findViewById<TextView>(R.id.users_position)

        var profile_card = view.findViewById<LinearLayout>(R.id.profile_card)

        var user_profile=view.findViewById<ImageView>(R.id.user_profile)


        var user_email=""





        val bundle = arguments
        val username = bundle!!.getString("username")
        val flatno = bundle!!.getString("flatno")
        val userposition = bundle!!.getString("userposition")
        val useremail = bundle!!.getString("useremail")

        tv_owners_name.text=username.toString()
        flat_no.text=flatno.toString()
        users_position.text=userposition.toString()
        user_email=useremail.toString()

        user_profile.setOnClickListener {
            val i =Intent(context,Activity_BaseActivity::class.java)
            i.putExtra("id", 0)
            i.putExtra("username","${tv_owners_name.text.toString()}")
            i.putExtra("flatno","${flat_no.text.toString()}")
            i.putExtra("email","${user_email.toString()}")
            i.putExtra("position","${users_position.text.toString()}")


            Log.d("passing_data_base","${tv_owners_name.text.toString()} email:${user_email} position:${users_position.text.toString()} flatno:${flat_no.text.toString()}")




            startActivity(i)
        }






        set_recview(recview, view, tv_owners_name, flat_no, profile_card,username,flatno,userposition,user_email)
        return view
    }


    private fun set_recview(
        recview: RecyclerView,
        view: View,
        tv_owners_name: TextView,
        flat_no: TextView,
        profile_card: LinearLayout,
        username: String?,
        flatno: String?,
        userposition: String?,
        user_email: String


    ) {

        recview.layoutManager = GridLayoutManager(view.context, 2)

        var datalist = ArrayList<ModelClass_Home>()


        datalist.add(
            ModelClass_Home(
                "Notice",
                R.drawable.ic_notice,
                "Society updates"
            )

        )

        datalist.add(
            ModelClass_Home(
                "Events",
                R.drawable.ic_calendar,
                "Social gathering"
            )

        )

        datalist.add(
            ModelClass_Home(
                "Complain",
                R.drawable.ic_badreview,
                "Improvements"
            )

        )

        datalist.add(
            ModelClass_Home(
                "Add User",
                R.drawable.adduserpng,
                "new user signup"
            )

        )

        datalist.add(
            ModelClass_Home(
                "Bill",
                R.drawable.ic_category,
                "Maintance"
            )

        )

        datalist.add(
            ModelClass_Home(
                "Emergency",
                R.drawable.ic_category,
                "24/7 helpline"
            )

        )


        var setadapter =
            Adapter_Home(view.context, datalist, object : Adapter_Home.CardClickedListener {
                override fun onCardClicked(position: Int) {
                    when (position) {

                        0 ->fun_notice(username,flatno,userposition,user_email)
                        1 -> Toast.makeText(context, "Event", Toast.LENGTH_SHORT).show()
                        2 -> fun_complains(username,flatno,userposition,user_email)
                        3 -> fun_adduser()
                    }
                }


            })
        recview.adapter = setadapter
    }

    private fun fun_notice(
        username: String?,
        flatno: String?,
        userposition: String?,
        user_email: String
    ) {
        var i = Intent(context, Activity_BaseActivity::class.java)
        i.putExtra("id", 1)
        i.putExtra("username","${username.toString()}")
        i.putExtra("flatno","${flatno.toString()}")
        i.putExtra("useremail","${user_email.toString()}")
        i.putExtra("userposition","${userposition.toString()}")
        startActivity(i)
    }

    private fun fun_adduser() {
        var i = Intent(context, Activity_Add_user::class.java)
        startActivity(i)
    }

    private fun fun_complains(
        username: String?,
        flatno: String?,
        userposition: String?,
        user_email: String
    ) {
        var i = Intent(context, Activity_BaseActivity::class.java)
        i.putExtra("id", 2)
        i.putExtra("username","${username.toString()}")
        i.putExtra("flatno","${flatno.toString()}")
        i.putExtra("useremail","${user_email.toString()}")
        i.putExtra("userposition","${userposition.toString()}")

        startActivity(i)
    }

}