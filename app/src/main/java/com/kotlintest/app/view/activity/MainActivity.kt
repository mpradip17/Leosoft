package com.kotlintest.app.view.activity

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.app.washeruser.repository.Status
import com.kotlintest.app.R
import com.kotlintest.app.baseClass.BaseActivity.BaseActivity
import com.kotlintest.app.databinding.ActivityMainBinding
import com.kotlintest.app.model.Sample
import com.kotlintest.app.network.Response
import com.kotlintest.app.utility.RecyclerItemClickListenr
import com.kotlintest.app.view.adapter.ImageAdapter
import com.kotlintest.app.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun layoutId(): Int = R.layout.activity_main

    private val homeViewModel by viewModel<HomeViewModel>()

    private var dataList: ArrayList<Sample> = ArrayList()

    private var loading = false
    private var adapter: ImageAdapter? = null
    override fun initView(mViewDataBinding: ViewDataBinding?) {
        binding.viewModel = homeViewModel
        adapter = ImageAdapter(dataList)
        binding.adapter = adapter


        if (isOnline(this)){
            homeViewModel.apiCall()
        }else{
            var mList = homeViewModel.loadAllUser()!!
            dataList.clear()
            dataList.addAll(mList)
            adapter?.notifyDataSetChanged()

        }


        binding.recycleView.addOnItemTouchListener(RecyclerItemClickListenr(this,  binding.recycleView, object : RecyclerItemClickListenr.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(baseContext, SecondActivity::class.java)
                intent.putExtra("email", dataList[position].name)
                intent.putExtra("username",  dataList[position].email)
                startActivity(intent)
            }
            override fun onItemLongClick(view: View?, position: Int) {

            }
        }))
        homeViewModel.response().observe(this, Observer {
            processResponse(it)
        })
    }


    private fun processResponse(response: Response) {
        when (response.status) {
            Status.SUCCESS -> {
                Log.e("response", "SUCCESS")

                loading = true
                dataList.addAll(response.data!!)
                adapter?.notifyDataSetChanged()


                Log.e("response", "SUCCESS")

            }
            Status.LOADING -> {
                Log.e("response", "LOADING")

                if (binding.loadingProgressXml.visibility == View.GONE) {

                    binding.loadingProgressXml.visibility = View.VISIBLE
                }


            }
            Status.DISMISS -> {
                Log.e("response", "DISMISS")

                if (binding.loadingProgressXml.visibility == View.VISIBLE)
                    binding.loadingProgressXml.visibility = View.GONE

            }
            Status.ERROR -> {
                Log.e("response", "ERROR")

                dataList.clear()
            }


        }

    }
}
