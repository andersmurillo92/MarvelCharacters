package com.grability.marvelcharacters.views.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.grability.marvelcharacters.R
import com.grability.marvelcharacters.utils.Animations
import com.grability.marvelcharacters.views.adapters.CharactersAdapter
import com.grability.marvelcharacters.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.grability.marvelcharacters.data.model.ResultsModel
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.collections.ArrayList


class HomeActivity: BaseActivity() {

    lateinit var viewModel: HomeViewModel
    var adapter: CharactersAdapter? = null
    var animations = Animations()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initializeView()
        initializeViewModel()
    }

    override fun onResume() {
        super.onResume()
        showProgressDialog(getString(R.string.message_title_getting_characters), getString(R.string.message_please_wait), indeterminate = true, cancelable = false)
        makeRequest()
        viewModel.singleLiveEvent.observe(this, Observer {
            if(swipeRefresh.isRefreshing)
                swipeRefresh.isRefreshing = false
            else
                hideProgressDialog()
            when(it){
                is HomeViewModel.ViewEvent.ResponseSuccess -> {
                    updateList()
                }
                is HomeViewModel.ViewEvent.ResponseError -> {
                    showSimpleToast(it.error.toString())
                }
            }
        })
    }

    private fun initializeView(){
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            if (hasNetworkConnection()) {
                makeRequest()
            } else {
                swipeRefresh.isRefreshing = false
                showSimpleToast(resources.getString(R.string.message_not_connected))
            }
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    private fun updateList(){
        val list = ArrayList<ResultsModel>()
        viewModel.characterList.value?.forEach {
            list.add(it)
        }

        adapter = CharactersAdapter(list)
        recyclerView.adapter = adapter
        animations.runRecyclerAnimation(recyclerView)
    }

    private fun makeRequest(){
        val timeStamp = getTimeStamp()
        val md5Hash = getMd5Hash(timeStamp.toString()
                + resources.getString(R.string.private_apikey)
                + resources.getString(R.string.public_apikey))
        md5Hash?.let {
            viewModel.getCharacters(
                timeStamp,
                resources.getString(R.string.public_apikey),
                md5Hash,
                currentOffset
            )
        }
    }

    private fun getTimeStamp(): Long {
        return Calendar.getInstance().timeInMillis
    }

    private fun getMd5Hash(input: String?): String? {
        return try {
            val md = MessageDigest.getInstance("MD5")
            val messageDigest = md.digest(input?.toByteArray())
            val number = BigInteger(1, messageDigest)
            var md5 = number.toString(16)

            while (md5.length < 32)
                md5 = "0$md5"

            md5
        } catch (e: NoSuchAlgorithmException) {
            Log.e("MD5", e.localizedMessage)
            null
        }
    }
}