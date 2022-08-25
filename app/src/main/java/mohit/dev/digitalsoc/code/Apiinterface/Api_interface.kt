package mohit.dev.digitalsoc.code.Apiinterface

import mohit.dev.digitalsoc.code.Model.Model_noticedb
import mohit.dev.digitalsoc.code.Model.Model_usercomplain
import mohit.dev.digitalsoc.code.Model.Model_userdb
import retrofit2.Call
import retrofit2.http.*

interface Api_interface {

    @FormUrlEncoded
    @POST("user_register.php")
    fun registerStudent(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("flatno") flatno: String,
        @Field("position") position: String
    ): Call<List<Model_userdb>>


    @GET("user_login.php")
    fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<List<Model_userdb>>


    @GET("subject.php")
    fun subject(): Call<List<Model_usercomplain>>

    @FormUrlEncoded
    @POST("complain.php")
    fun complain(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("flatno") flatno: String,
        @Field("complains") complains: String
    ): Call<List<Model_usercomplain>>


    @FormUrlEncoded
    @POST("delete_complain.php")
    fun delete_complain(
        @Field("id") id: Int
    ): Call<List<Model_usercomplain>>

    @GET("user_complains.php")
    fun user_complains(
        @Query("email") email: String
    ): Call<List<Model_usercomplain>>


    @GET("get_notice.php")
    fun get_notice(): Call<List<Model_noticedb>>


    @FormUrlEncoded
    @POST("post_notice.php")
    fun post_notice(
        @Field("title") title: String,
        @Field("notice") notice: String,
        @Field("date") date: String
    ): Call<List<Model_noticedb>>


}