package fr.efficom.formation.ppe1719

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.efficom.formation.ppe1719.api.Photo

class PhotosAdapter(val photos : List<Photo>): RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        //inflater permet de charger une vue depuis un layout en xml
        val inflater = LayoutInflater.from(parent.context)
        // chargement du layout depuis le xml
        val photoItemView = inflater.inflate(R.layout.photo_item_layout, parent, false)
        // creation du view holder qui va mettre en cache cette vue ainsi que ses sous-vues
        val photoViewHolder = PhotoViewHolder(photoItemView)
        return photoViewHolder
    }
    // compter d'element dans la liste
    override fun getItemCount(): Int {
         return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        // récupérer dans une constante la photo courante
         val photo = photos[position]
        //mettre a jour le texte du like(pour l'instant c'est la date de prise)
             holder.likeTextView.text = photo.date_prise
        //mettre a jour la photo
        Glide.with(holder.itemView.context).load(photo.url).into(holder.photoImageView)
    }


}