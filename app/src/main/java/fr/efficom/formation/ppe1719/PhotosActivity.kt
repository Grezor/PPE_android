package fr.efficom.formation.ppe1719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import fr.efficom.formation.ppe1719.api.BornesService
import fr.efficom.formation.ppe1719.api.Photo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_photos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosActivity : AppCompatActivity() {

    val bornesService: BornesService

    init {
         bornesService = createBorneService()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        val code = intent.getStringExtra("CODE");
        Log.d("PhotoActivity", "code event: $code");

        showPhotosAsList(code)

    }

    fun showPhotosAsList(codeEvent: String){

        val request = bornesService.getPhotoForEventAsList(codeEvent)
        request.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.w("bornesService", "Echec loginUser")
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val photos= response.body()
                if(photos!= null){
                    Log.d("PhotosActivity", "Photos: $photos")
                    // Création de l'adapter
                    val PhotosAdapter = PhotosAdapter(photos)
                    // Création de l'adapter et on le renseigne dans le recycler
                    photoRecyclerView.adapter = PhotosAdapter(photos)
                    photoRecyclerView.layoutManager = LinearLayoutManager(this@PhotosActivity)
                }
            }
        })
    }



}
