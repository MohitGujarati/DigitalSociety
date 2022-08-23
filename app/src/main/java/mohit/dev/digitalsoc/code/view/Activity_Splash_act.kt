package mohit.dev.digitalsoc.code.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import mohit.dev.digitalsoc.R
import mohit.dev.digitalsoc.code.Apiinterface.Api_interface
import mohit.dev.digitalsoc.code.Model.Model_userdb
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Activity_Splash_act : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var loginlayout=findViewById<RelativeLayout>(R.id.loginlayout)
        var splash_screen=findViewById<RelativeLayout>(R.id.splash_screen)



        var btnlogin = findViewById<MaterialButton>(R.id.btn_login)
        var user_email = findViewById<EditText>(R.id.eduser_email)
        var user_password = findViewById<EditText>(R.id.eduser_password)

        var user_name: String = ""
        var user_flatno: String = ""
        var user_position: String = ""
        var pass_email:String=""




        Handler(Looper.getMainLooper()).postDelayed({
            loginlayout.visibility= View.VISIBLE
            splash_screen.visibility= View.GONE
        },3000)


        btnlogin.setOnClickListener {

            var retrofit = Retrofit.Builder().baseUrl("https://mohitgapp.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api_interface::class.java)


            var result = retrofit.login(user_email.text.toString(), user_password.text.toString())


            result.enqueue(object : Callback<List<Model_userdb>?> {
                override fun onResponse(
                    call: Call<List<Model_userdb>?>,
                    response: Response<List<Model_userdb>?>
                ) {

                    var response = response.body()

                    for (data in response!!) {
                        Log.d(
                            "mydata",
                            "welcome ${data.name} ${data.email}  ${data.password} ${data.flatno} ${data.position}"
                        )
                        Toast.makeText(applicationContext, "Successful login", Toast.LENGTH_SHORT).show()


                        loginlayout.visibility = View.GONE

                        user_name = "${data.name}"
                        user_flatno = "${data.flatno}"
                        user_position = "${data.position}"
                        pass_email="${data.email}"

                        Toast.makeText(this@Activity_Splash_act, "email :- ${data.email}, emaildata :- ${data.email}", Toast.LENGTH_SHORT).show()

                        val i=Intent(this@Activity_Splash_act,Activity_MainActivity::class.java)
                        Toast.makeText(this@Activity_Splash_act, "From splash screen", Toast.LENGTH_SHORT).show()
                        i.putExtra("splash_username","$user_name")
                        i.putExtra("splash_flatno","$user_flatno")
                        i.putExtra("splash_position","$user_position")
                        i.putExtra("splash_email","${pass_email}")
                        startActivity(i)
                    }
                }

                override fun onFailure(call: Call<List<Model_userdb>?>, t: Throwable) {
                    Log.d("error", "MainActivity-69")
                }

            })

        }




    }
}