package com.boyu.emove.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.boyu.emove.R
import com.boyu.emove.home.HomeFragment
import com.boyu.emove.info.InfoFragment
import com.boyu.emove.main.viewmodel.MainViewModel
import com.boyu.emove.orderlist.OrderListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
//    private var homeFragment: HomeFragment? = null
//    private var infoFragment: InfoFragment? = null
//    private var orderListFragment: OrderListFragment? = null
//    private var fragmentList: Array<Fragment?> = arrayOfNulls(3)
//    private var seletedFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        //enable the back button
        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController)
//        NavigationUI.setupWithNavController(bnv_bottom_navigation, navController)


        bnv_bottom_navigation.selectedItemId = R.id.infoFragment
//        bnv_bottom_navigation.setOnNavigationItemSelectedListener {item ->
////            NavigationUI.onNavDestinationSelected(item,
////                    Navigation.findNavController(this, R.id.nav_host_fragment))
////                    || super.onOptionsItemSelected(item)
//
////            val navi =  Navigation.findNavController(this, R.id.nav_host_fragment)
//
//
//            when(item.itemId) {
//                com.boyu.emove.R.id.homeFragment -> {
////                    switchTab(0)
//                     supportActionBar?.hide()
////                    navi.navigate(R.id.homeFragment)
//                }
//                com.boyu.emove.R.id.infoFragment -> {
////                    switchTab(1)
//                    supportActionBar?.show()
////                    navi.navigate(R.id.infoFragment)
//                }
//                com.boyu.emove.R.id.orderListFragment -> {
////                    switchTab(2)
//                    supportActionBar?.hide()
////                    navi.navigate(R.id.orderListFragment)
//                }
//            }
//            return@setOnNavigationItemSelectedListener true
//
//
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }

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
