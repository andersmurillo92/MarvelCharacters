package com.grability.marvelcharacters.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grability.marvelcharacters.data.model.ResultsModel
import kotlin.collections.ArrayList

class PaginationAdapter(listOfResults: List<ResultsModel>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = ArrayList(listOfResults)
    private val loading = 0
    private val item = 1
    private var isLoadingAdded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            item -> {
                viewHolder = CharacterViewHolder(inflater, parent)
            }

            loading -> {
                viewHolder = LoadingViewHolder(inflater, parent)
            }
        }

        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result: ResultsModel? = list?.get(position)
        when (getItemViewType(position)){
            item -> {
                result?.let { (holder as CharacterViewHolder).bind(it)}
            }

            loading -> {
                result?.let { (holder as LoadingViewHolder).bind(it)}
            }
        }
    }

    override fun getItemCount(): Int = if (list == null) 0 else list!!.size

    override fun getItemViewType(position: Int): Int {
        return if (position == list!!.size - 1 && isLoadingAdded) loading else item
    }

    fun isLoaderVisible(): Boolean {
        return isLoadingAdded
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(ResultsModel())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = list!!.size - 1
        val result = getItem(position)

        if (result != null) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    private fun add(resultsModel: ResultsModel) {
        list.add(resultsModel)
        notifyItemInserted(list!!.size - 1)
    }

    fun addAll(results: List<ResultsModel>) {
        for (result in results) {
            add(result)
        }
    }

    private fun getItem(position: Int): ResultsModel? {
        return list!![position]
    }
}