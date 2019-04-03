package fr.efficom.formation.ppe1719

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PhotoViewHolder(rootView: View) :RecyclerView.ViewHolder(rootView){

    val likeTextView: TextView
    val photoImageView: ImageView


    init {
        likeTextView = rootView.findViewById(R.id.likeItemTextView)
        photoImageView = rootView.findViewById(R.id.photoItemImageView)
    }


}