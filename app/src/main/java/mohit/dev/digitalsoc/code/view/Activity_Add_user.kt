package mohit.dev.digitalsoc.code.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.button.MaterialButton
import mohit.dev.digitalsoc.R
import mohit.dev.digitalsoc.code.Apiinterface.Api_interface
import mohit.dev.digitalsoc.code.Model.Model_userdb
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Activity_Add_user : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)


        var edname = findViewById<EditText>(R.id.ed_name)
        var edemail = findViewById<EditText>(R.id.ed_Email)
        var edpassword = findViewById<EditText>(R.id.ed_Password)
        var edsubject = findViewById<TextView>(R.id.tv_subject)
        var btnsignup = findViewById<MaterialButton>(R.id.btnlogin)

        var radio_grp = findViewById<RadioGroup>(R.id.radio_grp)

        var position=""

        radio_grp.setOnCheckedChangeListener{radio_grp,i ->
            when(i){
                R.id.checkbox_member->{
                    position="Member"
                }
                R.id.checkbox_admin->{
                   position="Admin"
                }
            }
        }



        btnsignup.setOnClickListener {

            var retrofit = Retrofit.Builder().baseUrl("https://mohitgapp.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api_interface::class.java)




            var result = retrofit.registerStudent(
                edname.text.toString(),
                edemail.text.toString(),
                edpassword.text.toString(),
                edsubject.text.toString(),
                position.toString()
            )


            result.enqueue(object : Callback<List<Model_userdb>?> {
                override fun onResponse(
                    call: Call<List<Model_userdb>?>,
                    response: Response<List<Model_userdb>?>
                )
                {
                    Toast.makeText(applicationContext, "Data Inserted successfully", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<List<Model_userdb>?>, t: Throwable) {
//                    Toast.makeText(applicationContext, " Failed to Inserted Data", Toast.LENGTH_SHORT).show()
//                    Log.d("errordata","$t")

                }

            })


        }
    }
}