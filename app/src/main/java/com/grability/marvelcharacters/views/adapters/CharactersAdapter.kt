package com.grability.marvelcharacters.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grability.marvelcharacters.data.model.ResultsModel

class CharactersAdapter(private val list: List<ResultsModel>?) : RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val result: ResultsModel? = list?.get(position)
        result?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = list!!.size
}