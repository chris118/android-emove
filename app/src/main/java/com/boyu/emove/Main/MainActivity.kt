package com.boyu.emove.Main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.boyu.emove.Base.BaseActivity
import com.boyu.emove.R
import com.boyu.emove.R.id.btn_increment
import com.boyu.emove.R.id.tv_counter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.counter.observe(this, Observer { counter ->
            if (counter != null) {
                Log.d(TAG, counter.toString())
                tv_counter.text = counter.toString()
            }
        })

        btn_increment.setOnClickListener {
            mainViewModel.increment()
        }
    }
}
