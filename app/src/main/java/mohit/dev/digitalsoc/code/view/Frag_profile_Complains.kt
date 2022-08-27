package mohit.dev.digitalsoc.code.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mohit.dev.digitalsoc.R
import mohit.dev.digitalsoc.code.Adapter.Adapter_profileComplain
import mohit.dev.digitalsoc.code.Apiinterface.Api_interface
import mohit.dev.digitalsoc.code.Model.Model_usercomplain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Frag_profile_Complains(comp_email: String) : Fragment() {
    val BASE_URL = "https://mohitgapp.000webhostapp.com/"


    var emails = comp_email

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        var view = inflater.inflate(R.layout.fragment_frag_profile__complains, container, false)


        var profile_complains = view.findViewById<RecyclerView>(R.id.profile_complain)


        load_complains(profile_complains, view, emails)



        return view
    }


    private fun load_complains(
        recComplain: RecyclerView,
        view: View,
        emails: String,

        ) {

        recComplain.layoutManager = LinearLayoutManager(context)

        //retrieving data from 000

        var retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api_interface::class.java)


        var result = retrofit.user_complains("$emails")


        var usercomplaindataitem: ArrayList<Model_usercomplain> = ArrayList()

        result.enqueue(object : Callback<List<Model_usercomplain>?> {
            override fun onResponse(
                call: Call<List<Model_usercomplain>?>,
                response: Response<List<Model_usercomplain>?>
            ) {

                Log.d("recview", "$response")



                usercomplaindataitem =
                    response.body() as ArrayList<Model_usercomplain> /* = java.util.ArrayList<mohit.dev.digitalsoc.code.Model.model_complains> */

                var adapter = Adapter_profileComplain(
                    view.context,
                    usercomplaindataitem,
                    object : Adapter_profileComplain.btnclicked {
                        override fun onSpeakerClicked(position: Int, complains: String) {
                            Toast.makeText(context, "Speaker", Toast.LENGTH_SHORT).show()
                        }

                        override fun onEditClicked(
                            position: Int,
                            id: Int,
                            complains: String,
                            name: String,
                            flatno: String
                        ) {
                            Toast.makeText(context, "Editing $id", Toast.LENGTH_SHORT).show()

                            var retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(Api_interface::class.java)

                            var result = retrofit.user_updatecomplains(
                                emails,
                                complains,
                                id
                            )

                            result.enqueue(object : Callback<List<Model_usercomplain>?> {
                                override fun onResponse(
                                    call: Call<List<Model_usercomplain>?>,
                                    response: Response<List<Model_usercomplain>?>
                                ) {


                                    Log.d("complainsupdate", "${id} ${emails} ${complains}")


                                }

                                override fun onFailure(
                                    call: Call<List<Model_usercomplain>?>,
                                    t: Throwable
                                ) {
                                    Toast.makeText(context, "Cant update data", Toast.LENGTH_SHORT)
                                        .show()
                                }

                            })


                        }

                        override fun onDeleteClicked(
                            position: Int,
                            id: Int,
                            complains: String,
                            name: String,
                            flatno: String
                        ) {
                            Toast.makeText(context, "Delete $id", Toast.LENGTH_SHORT).show()

                            var retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(Api_interface::class.java)


                            var result = retrofit.delete_complain(
                                id
                            )

                            result.enqueue(object : Callback<List<Model_usercomplain>?> {
                                override fun onResponse(
                                    call: Call<List<Model_usercomplain>?>,
                                    response: Response<List<Model_usercomplain>?>
                                ) {
                                    Toast.makeText(
                                        context,
                                        "Data at $id deleted successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                override fun onFailure(
                                    call: Call<List<Model_usercomplain>?>,
                                    t: Throwable
                                ) {
                                    Toast.makeText(context, "$t", Toast.LENGTH_SHORT).show()
                                }

                            })
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

}

