package fr.efficom.formation.ppe1719

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
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
        bornesService = createBorneService()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        login_button.setOnClickListener {
        // pour lancer une nouvelle activite
        // premier paramètre l'instance, la page qui a cette class
        val intent = Intent(this@MainActivity, PhotosActivity::class.java )
            //transmettre l'info
        intent.putExtra("CODE", codeEventEditText.text.toString())
        // traite comme un message d'ouverture
        startActivity(intent)



//            showPhotosAsList()
//            login()

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
//    fun showPhotosAsList(){
//        val codeEvent = codeEventEditText.text.toString()
//        val request = bornesService.getPhotoForEventAsList(codeEvent)
//        request.enqueue(object : Callback<List<Photo>>{
//            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
//                val result = response.body()
//
//                if (result!= null){
//                    // aficher l'image
//                    var length = result.size
//                    var imagePhoto:String = ""
//
//                    for (i in 0 until length){
//                        imagePhoto = imagePhoto + "\n" + result.get(i).url + " " + result.get(i).date_prise
//
//                    //
//                    }
//                    // Affiche l'image en json
//                    img_text_view.text = imagePhoto
//                    Glide.with(this@MainActivity)
//                        .load(result[0].url)
//                        .into(imageView);
//                }
//            }
//
//        })
//    }



//    fun showPhotosAsList(){
//        val codeEvent = codeEventEditText.text.toString()
//        val request = bornesService.getPhotoForEventAsList(codeEvent)
//
//        request.enqueue(object : Callback<List<Photo>> {
//            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
//                Log.w("bornesService", "Echec loginUser")
//            }
//
//            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
//                val photo = response.body()
//                if(photo!= null){
//
//                    token_text_view.text = photo.joinToString(" - ")
//                    Glide.with(this@MainActivity)
//                        .load(photo[1].url)
//                        .into(imageView);
//
//                }
//            }
//        })
//    }












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
