package com.kotlintest.app.view.activity

import android.util.Log
import androidx.databinding.ViewDataBinding
import com.kotlintest.app.R
import com.kotlintest.app.baseClass.BaseActivity.BaseActivity
import com.kotlintest.app.databinding.ActivityNextBinding


class SecondActivity : BaseActivity<ActivityNextBinding>() {


    override fun layoutId(): Int = R.layout.activity_next


    override fun initView(mViewDataBinding: ViewDataBinding?) {

        val email = intent.getStringExtra("email")
        val username = intent.getStringExtra("username")
     Log.e("email",""+email)
        Log.e("username",""+username)

        binding.username.text = username

        binding.status.text = email


    }

}
