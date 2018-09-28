package com.boyu.emove.main.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseActivity
import com.boyu.emove.home.HomeFragment
import com.boyu.emove.login.ui.LoginActivity
import com.boyu.emove.utils.SharedPreferencesUtil
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private var token by SharedPreferencesUtil(this@MainActivity,"token","")

    private var homeFragment: HomeFragment? = null
    private var infoFragment: NavHostFragment? = null
    private var orderlistFragment: NavHostFragment? = null
    private var fragmentList: MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        requestPermission()
        setupNavigationController()
        checkToken()
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.fl_container).navigateUp()
    }

    override fun onBackPressed() {
       val navi = Navigation.findNavController(this, R.id.fl_container)
        val destId = navi.currentDestination?.id ?: 0
        if (destId == R.id.homeFragment || destId == R.id.infoFragment || destId == R.id.orderListFragment){
            super.onBackPressed()
        }else {
            Navigation.findNavController(this, R.id.fl_container).navigateUp()
        }
    }

//    fun showActionBar(){
//        supportActionBar?.show()
//    }

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
        // enable the bottom navi
//        NavigationUI.setupWithNavController(bnv_bottom_navigation, navController)

        //enable the back button
        setSupportActionBar(toolbar)

        switchTo(0)

        bnv_bottom_navigation.setOnNavigationItemSelectedListener {item ->
//            NavigationUI.onNavDestinationSelected(item,
//                    Navigation.findNavController(this, R.id.nav_host_fragment))
//                    || super.onOptionsItemSelected(item)

            when(item.itemId) {
                com.boyu.emove.R.id.homeFragment -> {
                    switchTo(0)
                }
                com.boyu.emove.R.id.infoFragment -> {
                    switchTo(1)
                }
                com.boyu.emove.R.id.orderListFragment -> {
                    switchTo(2)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    fun switchTo(index: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if(homeFragment == null){
            homeFragment = HomeFragment()
            fragmentTransaction.add(R.id.fl_container, homeFragment!!)
            fragmentList.add(homeFragment!!)
        }
        if(infoFragment == null){
            infoFragment = NavHostFragment.create(R.navigation.nav_graph)
            fragmentTransaction.add(R.id.fl_container, infoFragment!!)
            fragmentList.add(infoFragment!!)
        }
        if(orderlistFragment == null) {
            orderlistFragment = NavHostFragment.create(R.navigation.nav_graph_2)
            fragmentTransaction.add(R.id.fl_container, orderlistFragment!!)
            fragmentList.add(orderlistFragment!!)
        }

        for ((i, fragment) in fragmentList.withIndex()){
            if(i == index){
                fragmentTransaction.attach(fragment)
            }else {
                fragmentTransaction.detach(fragment)
            }
        }
        fragmentTransaction.commit()
    }
}
