package com.grability.marvelcharacters.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.grability.marvelcharacters.R
import com.grability.marvelcharacters.data.model.ResultsModel
import com.squareup.picasso.Picasso

class CharacterViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_character, parent, false)) {

    private var context: Context? = null
    var imageView: ImageView? = null
    var description: TextView? = null
    var title: TextView? = null

    init {
        context = parent.context
        imageView = itemView.findViewById(R.id.iconIv)
        description = itemView.findViewById(R.id.subtitle_txt)
        title = itemView.findViewById(R.id.title_txt)
    }

    fun bind(results: ResultsModel) {
        Picasso.with(context).isLoggingEnabled = true
        Picasso.with(context)
            .load(results.thumbnailModel?.path?.replace("http://","https://") + "/landscape_incredible." + results.thumbnailModel?.extension)
            .into(imageView)

        title?.text = results.name

        if(results.description != null && !results.description.equals("")){
            description?.text = results.description

            val params = description?.layoutParams
            params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
            description?.layoutParams = params

            val density = context?.resources?.displayMetrics?.density
            density?.let {
                val paddingPixel = (8 * density).toInt()
                description?.setPadding(paddingPixel)
            }
        }

        itemView.setOnClickListener {

        }
    }
}