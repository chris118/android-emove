package com.boyu.emove.main.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseActivity
import com.boyu.emove.login.ui.LoginActivity
import com.boyu.emove.utils.SharedPreferencesUtil
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private var token by SharedPreferencesUtil(this@MainActivity,"token","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        requestPermission()
        setupNavigationController()
        checkToken()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        this.bnv_bottom_navigation.selectedItemId = R.id.homeFragment

    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }
    override fun onBackPressed() {
//        bnv_bottom_navigation.visibility = View.VISIBLE


       val navi = Navigation.findNavController(this, R.id.nav_host_fragment)
        val destId = navi.currentDestination?.id ?: 0
        if (destId == R.id.homeFragment || destId == R.id.infoFragment || destId == R.id.orderListFragment){
           return
        }
        super.onBackPressed()
    }

    fun showActionBar(){
        supportActionBar?.show()
    }

    private fun requestPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.CALL_PHONE)
                .onGranted {

                }
                .onDenied {

                }
                .start()
    }

    private fun checkToken() {
        Log.d(TAG, token)
        if (token.length == 0) {
            startActivity<LoginActivity>()
        }
    }

    private fun setupNavigationController() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        //enable the back button
        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(bnv_bottom_navigation, navController)


//        bnv_bottom_navigation.setOnNavigationItemSelectedListener {item ->
//            NavigationUI.onNavDestinationSelected(item,
//                    Navigation.findNavController(this, R.id.nav_host_fragment))
//                    || super.onOptionsItemSelected(item)
//
//            when(item.itemId) {
//                com.boyu.emove.R.id.homeFragment -> {
//                    supportActionBar?.show()
//
//                    //隐藏返回
//                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
//                }
//                com.boyu.emove.R.id.infoFragment -> {
//                    supportActionBar?.show()
//                }
//                com.boyu.emove.R.id.orderListFragment -> {
//                    supportActionBar?.show()
//                }
//            }
//            return@setOnNavigationItemSelectedListener true
//        }
    }
}
