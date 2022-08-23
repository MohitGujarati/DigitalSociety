package mohit.dev.digitalsoc.code.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import mohit.dev.digitalsoc.R
import mohit.dev.digitalsoc.code.Apiinterface.Api_interface
import mohit.dev.digitalsoc.code.Model.Model_noticedb
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Frag_Add_notice : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view= inflater.inflate(R.layout.fragment_frag__add_notice, container, false)


        var btnpost_notice=view.findViewById<MaterialButton>(R.id.btnpost_notice)
        var ed_Notice=view.findViewById<EditText>(R.id.ed_Notice)
        var ed_date=view.findViewById<EditText>(R.id.ed_date)
        var ed_title=view.findViewById<EditText>(R.id.ed_title)
        val tv_name = view.findViewById<TextView>(R.id.tv_name)
        val tv_flatno = view.findViewById<TextView>(R.id.tv_flatno)

        val bundle = arguments
        val username = bundle!!.getString("compuser")
        val flatno = bundle!!.getString("compflatno")
        val position = bundle!!.getString("compposition")


        tv_flatno.text=flatno.toString()
        tv_name.text=username.toString()


        btnpost_notice.setOnClickListener {
            var retrofit = Retrofit.Builder().baseUrl("https://mohitgapp.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api_interface::class.java)

            var get_title=ed_title.text.toString()
            var get_date=ed_date.text.toString()
            var get_notice=ed_Notice.text.toString()


            var result = retrofit.post_notice(

                "$get_title",
                "$get_notice",
                "$get_date",

            )


            result.enqueue(object : Callback<List<Model_noticedb>?> {
                override fun onResponse(
                    call: Call<List<Model_noticedb>?>,
                    response: Response<List<Model_noticedb>?>
                ) {
                    Toast.makeText(
                        context,
                        "Data Inserted successfully",
                        Toast.LENGTH_SHORT
                    ).show()


                }

                override fun onFailure(call: Call<List<Model_noticedb>?>, t: Throwable) {
//                    Toast.makeText(applicationContext, " Failed to Inserted Data", Toast.LENGTH_SHORT).show()
//                    Log.d("errordata","$t")

                }

            }  )
            Toast.makeText(context, "complain posted", Toast.LENGTH_LONG).show()
            ed_Notice.text.clear()
            ed_title.text.clear()
            ed_date.text.clear()
        }

        return view
    }

}