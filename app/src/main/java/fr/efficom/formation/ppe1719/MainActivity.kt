package fr.efficom.formation.ppe1719

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.efficom.formation.ppe1719.api.BornesService
import fr.efficom.formation.ppe1719.api.Photo
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val bornesService: BornesService

    init {
        val urlApi = "https://ibm-patisserie-mysql-php-20190301081146073-rested-kudu.eu-gb.mybluemix.net/"
        val retrofit = Retrofit.Builder().baseUrl(urlApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bornesService = retrofit.create(BornesService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        login_button.setOnClickListener {
            showPhotosAsList()
            login()

        }

    }
    ////////////////////////////////
    // Function PHOTOS
    ////////////////////////////////
//    fun showPhotos() {
//        //CodeEvent -> recupère les informations du champs input CodeEvent
//        // Exemple : SONA affiche image autre affiche rien
//        val codeEvent = codeEventEditText.text.toString()
//
//        val request = bornesService.getPhotoForEventAsList(codeEvent)
//        request.enqueue(object : Callback<List<Photo>> {
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.w("bornesService", "Echec photos")
//            }
//
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                val body = response.body()
//                if (body != null) {
//                    // Affiche les images
//                    img_text_view.text = body.string()
//                }
//            }
//        })
//
//    }

    ////////////////////////////////
    // Function
    ////////////////////////////////
    fun showPhotosAsList(){
        val codeEvent = codeEventEditText.text.toString()
        val request = bornesService.getPhotoForEventAsList(codeEvent)
        request.enqueue(object : Callback<List<Photo>>{
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val result = response.body()

                if (result!= null){
                    // aficher l'image
                    var length = result.size
                    var str:String = ""

                    for (i in 0 until length){
                        str = str + "\n" + result.get(i).url + " " + result.get(i).date_prise
                    }

                }
            }

        })
    }

    ////////////////////////////////
    // Function LOGIN
    ////////////////////////////////
    fun login() {
        val tokenRequest = bornesService.loginUser("toto")
        tokenRequest.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("bornesService", "Echec loginUser")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val body = response.body()
                if (body != null) {
                    // Affiche le token
                    token_text_view.text = body.string()
                }
            }
        })
    }


}
