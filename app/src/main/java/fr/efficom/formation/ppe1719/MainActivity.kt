package fr.efficom.formation.ppe1719

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    val bornesService: BornesService

    init {
        val urlApi = "https://ibm-patisserie-mysql-php-20190301081146073-rested-kudu.eu-gb.mybluemix.net/"
        val retrofit = Retrofit.Builder().baseUrl(urlApi).build()

        bornesService = retrofit.create(BornesService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        login_button.setOnClickListener {
            showPhotos()
            login()
        }

    }
//////////////////////////////PHOTOS ///////////////////////
    fun showPhotos(){
        val request = bornesService.getPhotoForEvent("SONA")
        request.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("bornesService", "Echec loginUser")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val body = response.body()
                if (body != null) {
                    img_text_view.text = body.string()
                }
            }
        })
}
    ////////////////////////////// LOGIN  ///////////////////////
    fun login() {
        val tokenRequest = bornesService.loginUser("toto")
        tokenRequest.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("bornesService", "Echec loginUser")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val body = response.body()
                if (body != null) {
                    token_text_view.text = body.string()
                }
            }
        })
    }


}
