package fr.efficom.formation.ppe1719.api

import android.provider.ContactsContract
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BornesService {
    // API LOGIN
    @GET("api/login_mobile.php")
    fun loginUser(@Query("login") login: String): Call<ResponseBody>

    // API PHOTO
    @GET("api/photos_event.php")
    fun getPhotoForEvent(@Query("code") login: String): Call<ResponseBody>

    // LIST PHOTO
    // Retourne sous forme d'un tableau
    @GET("api/photos_event.php")
    fun getPhotoForEventAsList(@Query("code") login: String): Call<List<Photo>>
}
