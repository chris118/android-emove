package com.boyu.emove.main.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.boyu.emove.Login.ui.LoginActivity
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseActivity
import com.boyu.emove.main.viewmodel.MainViewModel
import com.boyu.emove.utils.SharedPreferencesUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private var token by SharedPreferencesUtil(this@MainActivity,"token","")
    private var uid by SharedPreferencesUtil(this@MainActivity,"uid","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setupNavigationController()

        checkToken()

        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        bnv_bottom_navigation.visibility = View.VISIBLE
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }
    override fun onBackPressed() {
        bnv_bottom_navigation.visibility = View.VISIBLE
        super.onBackPressed()
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


        bnv_bottom_navigation.setOnNavigationItemSelectedListener {item ->
            NavigationUI.onNavDestinationSelected(item,
                    Navigation.findNavController(this, R.id.nav_host_fragment))
                    || super.onOptionsItemSelected(item)

            when(item.itemId) {
                com.boyu.emove.R.id.homeFragment -> {
                    supportActionBar?.hide()
                }
                com.boyu.emove.R.id.infoFragment -> {
                    supportActionBar?.show()
                }
                com.boyu.emove.R.id.orderListFragment -> {
                    supportActionBar?.hide()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }


//    private var homeFragment: HomeFragment? = null
//    private var infoFragment: InfoFragment? = null
//    private var orderListFragment: OrderListFragment? = null
//    private var fragmentList: Array<Fragment?> = arrayOfNulls(3)
//    private var seletedFragment: Fragment? = null

//    private fun switchTab(index: Int){
//        when(index) {
//            0 -> {
//                if(homeFragment == null){
//                    homeFragment = HomeFragment.newInstance()
//                    fragmentList[0] = homeFragment!!
//                }
//            }
//            1 -> {
//                if(infoFragment == null){
//                    infoFragment = InfoFragment.newInstance()
//                    fragmentList[1] = infoFragment!!
//                }
//            }
//            2 -> {
//                if(orderListFragment == null){
//                    orderListFragment = OrderListFragment.newInstance()
//                    fragmentList[2] = orderListFragment!!
//                }
//            }
//        }
//        val transaction = supportFragmentManager.beginTransaction()
//        var toFragment = fragmentList[index]
//
//        if(seletedFragment == null) {
//            transaction.add(R.id.fl_container, toFragment!!).commit()
//            seletedFragment = toFragment
//            return
//        }
//
//        if(!toFragment?.isAdded!!) {
//            transaction.add(R.id.fl_container, toFragment!!)
//        }
//        transaction.hide(seletedFragment!!).show(toFragment!!).commit()
//        seletedFragment = toFragment
//
//    }
}
