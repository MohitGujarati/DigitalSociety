package mohit.dev.digitalsoc.code.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import mohit.dev.digitalsoc.R

class MainActivity : AppCompatActivity() {

    var user_name: String = ""
    var user_flatno: String = ""
    var user_position: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        https://mohitgapp.000webhostapp.com/
         */
        var containerview = findViewById<LinearLayout>(R.id.containerview)

        val extras = intent.extras
        val add_id = extras?.getString("add_id")?.toInt()


        if (add_id != null) {
            getname(extras, add_id)
            load_homeFrag(containerview, user_name, user_flatno, user_position, extras, add_id)
        } else
            getname(extras, 0)
        load_homeFrag(containerview, user_name, user_flatno, user_position, extras, 0)


    }

    private fun getname(extras: Bundle?, add_id: Int) {

        if (extras != null) {
            val name = extras.getString("splash_username")
            val flatno = extras.getString("splash_flatno")
            val position = extras.getString("splash_position")


            user_name = "${name}"
            user_flatno = "${flatno}"
            user_position = "${position}"

        } else if (add_id != null) {
            val basename = extras?.getString("add_username")
            val baseflatno = extras?.getString("add_flatno")
            val baseposition = extras?.getString("add_position")


            user_name = "${basename}"
            user_flatno = "${baseflatno}"
            user_position = "${baseposition}"
        } else {

            val basename = extras?.getString("base_username")
            val baseflatno = extras?.getString("base_flatno")
            val baseposition = extras?.getString("base_position")


            user_name = "${basename}"
            user_flatno = "${baseflatno}"
            user_position = "${baseposition}"

        }

    }

    private fun load_homeFrag(
        containerview: LinearLayout,
        user_name: String,
        user_flatno: String,
        user_position: String,
        extras: Bundle?,
        add_id: Int

    ) {

        getname(extras, add_id)
        var Bundle = Bundle()
        containerview.visibility = View.VISIBLE
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragment1 = Frag_Home()

        Bundle.putString("username", "${user_name.toString()}")
        Bundle.putString("flatno", "${user_flatno.toString()}")
        Bundle.putString("userposition", "${user_position.toString()}")
        fragment1.arguments = Bundle

        fragmentTransaction.add(R.id.containerview, fragment1).commit()
    }

    override fun onBackPressed() {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finishAffinity();
        finish()
        super.onBackPressed()
    }
}