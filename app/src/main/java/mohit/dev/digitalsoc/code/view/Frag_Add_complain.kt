package mohit.dev.digitalsoc.code.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import mohit.dev.digitalsoc.R
import mohit.dev.digitalsoc.code.Apiinterface.Api_interface
import mohit.dev.digitalsoc.code.Model.Model_usercomplain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Frag_Add_complain : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag__add_complain, container, false)

        val btn_sendComplain = view.findViewById<Button>(R.id.btnsend)
        val tv_name = view.findViewById<TextView>(R.id.tv_name)
        val tv_flatno = view.findViewById<TextView>(R.id.tv_flatno)
        val ed_complains = view.findViewById<EditText>(R.id.ed_complains)


        //getting user data
        val bundle = arguments
        val username = bundle!!.getString("compuser")
        val flatno = bundle!!.getString("compflatno")
        val position = bundle!!.getString("compposition")
        val email = bundle!!.getString("compemail")

        //checking data recived
        Log.d("recived_data_addfragment","${username} email:${email} position:$position, flatno:${flatno}")

        //taking flat user data
        tv_flatno.text=flatno.toString()
        tv_name.text=username.toString()


        //adding complain to online database using retrofit

        btn_sendComplain.setOnClickListener {
            var retrofit = Retrofit.Builder().baseUrl("https://mohitgapp.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api_interface::class.java)

            var get_name=tv_name.text.toString()
            var get_flatno=tv_flatno.text.toString()
            var get_complains=ed_complains.text.toString()
            var get_email="${email.toString()}"

            var result = retrofit.complain(

                "$get_name",
                "$get_email",
                "$get_flatno",
                "$get_complains"
            )

         //   Log.d("recived_data_addfragment","${get_name} email:${get_email} position:not needed, flatno:${get_flatno}")

            result.enqueue(object : Callback<List<Model_usercomplain>?> {
                override fun onResponse(
                    call: Call<List<Model_usercomplain>?>,
                    response: Response<List<Model_usercomplain>?>
                ) {
                    Toast.makeText(
                        context,
                        "Data Inserted successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<List<Model_usercomplain>?>, t: Throwable) {
//                    Toast.makeText(applicationContext, " Failed to Inserted Data", Toast.LENGTH_SHORT).show()
//                    Log.d("errordata","$t")

                }

            }  )
            Toast.makeText(context, "complain posted", Toast.LENGTH_LONG).show()
            ed_complains.text.clear()
        }

        return view
    }


}