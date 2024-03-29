package mohit.dev.digitalsoc.code.view

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.tabs.TabLayout
import mohit.dev.digitalsoc.R
import mohit.dev.digitalsoc.code.Adapter.Adapter_complain
import mohit.dev.digitalsoc.code.Adapter.Adapter_notice
import mohit.dev.digitalsoc.code.Apiinterface.Api_interface
import mohit.dev.digitalsoc.code.Model.Model_noticedb
import mohit.dev.digitalsoc.code.Model.Model_usercomplain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class Activity_BaseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var tts: TextToSpeech? = null
    val BASE_URL = "https://mohitgapp.000webhostapp.com/"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        var btn_default = findViewById<ExtendedFloatingActionButton>(R.id.btn_addcomplain)

        var rec_complains = findViewById<RecyclerView>(R.id.rec_complains)
        // var rec_notice = findViewById<RecyclerView>(R.id.rec_notices)
        var title = findViewById<TextView>(R.id.click_title)


        var tab_layout = findViewById<LinearLayout>(R.id.tab_layout)
        var cardlayout = findViewById<LinearLayout>(R.id.cardlayout)

        var layout_profile = findViewById<LinearLayout>(R.id.layout_profile)


        tts = TextToSpeech(this, this)


        var Bundle = Bundle()
        var id = intent.extras?.get("id")
        var username = intent.extras?.get("username")
        var flatno = intent.extras?.get("flatno")
        var position = intent.extras?.get("userposition")
        var email = intent.extras?.get("useremail")


        var profile_email= intent.extras?.get("email")
        var profile_position= intent.extras?.get("position")
        var profile_username= intent.extras?.get("username")
        var profile_flatno= intent.extras?.get("flatno")

        var comp_username = ""
        var comp_flatno = ""
        var comp_position = ""
        var comp_email = ""


        comp_username = username.toString()
        comp_flatno = flatno.toString()
        comp_position = position.toString()
        comp_email = email.toString()


        Log.d(
            "recived_data_baseact",
            "${comp_username} email:${comp_email} position:${comp_position} flatno:${comp_flatno}"
        )

        when (id) {


            0 -> {
                profile_Act(
                    comp_flatno.toString(),
                    profile_username.toString(),
                    profile_position.toString(),
                    position.toString(),
                    tab_layout,
                    cardlayout,
                    layout_profile,
                    profile_email.toString()
                )
            }

            1 -> {
                load_notice(

                    rec_complains,
                    title,
                    btn_default,
                    comp_flatno,
                    comp_username,
                    comp_position,
                    position.toString()

                )


            }

            2 -> load_complain(
                rec_complains,
                title,
                btn_default,
                comp_flatno,
                comp_username,
                comp_position,
                comp_email
            )
        }


    }


    private fun profile_Act(
        comp_flatno: String,
        comp_username: String,
        comp_position: String,
        position: String,
        tab_layout: LinearLayout,
        cardlayout: LinearLayout,
        layout_profile: LinearLayout,
        comp_email: String
    ) {

        cardlayout.visibility = View.GONE

        var Bundle = Bundle()
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragment = Frag_Profile_card()


        Bundle.putString("baseact_to_Profile_username", "${comp_username}")
        Bundle.putString("baseact_to_Profile_flatno", "${comp_flatno}")
        Bundle.putString("baseact_to_Profile_position", "${comp_position}")
        Bundle.putString("baseact_to_Profile_email", "${comp_email}")

        fragment.arguments = Bundle
        Log.d("send_data_profileact", "${comp_username} email:${comp_email} position:${comp_position} flatno:${comp_flatno}")

        fragmentTransaction.add(R.id.layout_profile, fragment).commit()

    }



    //Recycler view for notice
    private fun load_notice(
        recComplain: RecyclerView,
        title: TextView,
        btn_default: ExtendedFloatingActionButton,
        comp_flatno: String,
        comp_username: String,
        comp_position: String,
        position: String
    ) {

        change_color(title, btn_default, 1)


        btn_default.setOnClickListener {
            btn_notice(btn_default, title, recComplain, comp_flatno, comp_username, comp_position)


        }
        recComplain.layoutManager = LinearLayoutManager(this)

        //retrieving data from 000

        var retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api_interface::class.java)


        var result = retrofit.get_notice()
        var complaindataitem: ArrayList<Model_noticedb> = ArrayList()

        result.enqueue(object : Callback<List<Model_noticedb>?> {
            override fun onResponse(
                call: Call<List<Model_noticedb>?>,
                response: Response<List<Model_noticedb>?>
            ) {

                Log.d("recview", "$response")

                complaindataitem =
                    response.body() as ArrayList<Model_noticedb> /* = java.util.ArrayList<mohit.dev.digitalsoc.code.Model.model_complains> */

                var adapter = Adapter_notice(
                    applicationContext,
                    complaindataitem,
                    object : Adapter_notice.SpeakerClicked {
                        override fun onSpeakerClicked(position: Int, complains: String) {

                            val text = complains.toString()
                            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
                            tts!!.setPitch(1.0f)
                            tts!!.setSpeechRate(1.4f)


                        }

                    }
                )
                recComplain.adapter = adapter


            }


            override fun onFailure(call: Call<List<Model_noticedb>?>, t: Throwable) {
                Log.d("error", "BaseActivity-75 $t")
            }

        })


    }

    //Recycler view for complain
    private fun load_complain(
        recComplain: RecyclerView,
        title: TextView,
        btn_default: ExtendedFloatingActionButton,
        comp_flatno: String,
        comp_username: String,
        comp_position: String,
        comp_email: String

    ) {

        change_color(title, btn_default, 2)
        btn_default.setOnClickListener {
            btn_complain(
                btn_default,
                title,
                recComplain,
                comp_flatno,
                comp_username,
                comp_position,
                comp_email
            )
        }

        recComplain.layoutManager = LinearLayoutManager(this)

        //retrieving data from 000

        var retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api_interface::class.java)


        var result = retrofit.subject()
        var complaindataitem: ArrayList<Model_usercomplain> = ArrayList()

        result.enqueue(object : Callback<List<Model_usercomplain>?> {
            override fun onResponse(
                call: Call<List<Model_usercomplain>?>,
                response: Response<List<Model_usercomplain>?>
            ) {

                Log.d("recview", "$response")

                complaindataitem =
                    response.body() as ArrayList<Model_usercomplain> /* = java.util.ArrayList<mohit.dev.digitalsoc.code.Model.model_complains> */

                var adapter = Adapter_complain(
                    applicationContext,
                    complaindataitem,
                    object : Adapter_complain.SpeakerClicked {
                        override fun onSpeakerClicked(position: Int, complains: String) {

                            val text = complains.toString()
                            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
                            tts!!.setPitch(1.0f)
                            tts!!.setSpeechRate(1.4f)
                        }

                    }
                )
                recComplain.adapter = adapter
            }


            override fun onFailure(call: Call<List<Model_usercomplain>?>, t: Throwable) {
                Log.d("error", "BaseActivity-75 $t")
            }

        })


    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage((Locale.US))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("mydata", "Not supported")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
    }


    private fun btn_complain(
        btn_default: ExtendedFloatingActionButton,
        title: TextView,
        recComplain: RecyclerView,
        comp_flatno: String,
        comp_username: String,
        comp_position: String,
        comp_email: String
    ) {

        var container = findViewById<LinearLayout>(R.id.container)
        container.visibility = View.VISIBLE
        btn_default.visibility = View.GONE
        title.text = "Post Complain"
        recComplain.visibility = View.GONE


        var Bundle = Bundle()
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragment = Frag_Add_complain()

        Bundle.putString("compuser", "${comp_username}")
        Bundle.putString("compflatno", "${comp_flatno}")
        Bundle.putString("compposition", "${comp_position}")
        Bundle.putString("compemail", "${comp_email}")

        fragment.arguments = Bundle



        fragmentTransaction.add(R.id.container, fragment).commit()
    }


    private fun btn_notice(
        btn_default: ExtendedFloatingActionButton,
        title: TextView,
        recComplain: RecyclerView,
        comp_flatno: String,
        comp_username: String,
        comp_position: String
    ) {

        var container = findViewById<LinearLayout>(R.id.container)
        container.visibility = View.VISIBLE
        btn_default.visibility = View.GONE
        title.text = "Post Notice"
        recComplain.visibility = View.GONE


        var Bundle = Bundle()
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragment = Frag_Add_notice()

        Bundle.putString("compuser", "${comp_username}")
        Bundle.putString("compflatno", "${comp_flatno}")
        Bundle.putString("compposition", "${comp_position}")

        fragment.arguments = Bundle

        fragmentTransaction.add(R.id.container, fragment).commit()
    }

    private fun change_color(
        title: TextView,
        btnDefault: ExtendedFloatingActionButton,
        id: Int
    ) {

        when (id) {
            1 -> {
                title.text = "Notice"
                title.setBackgroundColor(getColor(R.color.notice))
                btnDefault.setBackgroundColor(getColor(R.color.notice))
                btnDefault.text = "Notice"
            }
            2 -> {
                title.text = "Complains"
                title.setBackgroundColor(getColor(R.color.complain))
                btnDefault.setBackgroundColor(getColor(R.color.complain))
                btnDefault.text = "Complains"
            }
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        fun goback(
            comp_username: String,
            comp_flatno: String,
            comp_position: String,
            comp_email: String
        ) {
            var i = Intent(this, Activity_MainActivity::class.java)
            i.putExtra("base_username", "${comp_username}")
            i.putExtra("base_flatno", "${comp_flatno}")
            i.putExtra("base_position", "${comp_position}")
            i.putExtra("base_email", "${comp_email}")
            startActivity(i)
        }
    }

}