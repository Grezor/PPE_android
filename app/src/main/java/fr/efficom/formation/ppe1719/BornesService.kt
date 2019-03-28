package fr.efficom.formation.ppe1719

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BornesService {
    @GET("api/login_mobile.php")
    fun loginUser(@Query("login") login: String): Call<ResponseBody>

    @GET("api/photos_event.php")
    fun getPhotoForEvent(@Query("code") login: String): Call<ResponseBody>
}
