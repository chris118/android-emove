package com.boyu.emove.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.boyu.emove.R
import com.boyu.emove.home.HomeFragment
import com.boyu.emove.info.InfoFragment
import com.boyu.emove.main.viewmodel.MainViewModel
import com.boyu.emove.orderlist.OrderListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private var homeFragment: HomeFragment? = null
    private var infoFragment: InfoFragment? = null
    private var orderListFragment: OrderListFragment? = null
    private var fragmentList: Array<Fragment?> = arrayOfNulls(3)
    private var seletedFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        switchTab(0)

        bnv_bottom_navigation.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {
                com.boyu.emove.R.id.item_1 -> {
                    switchTab(0)
                }
                com.boyu.emove.R.id.item_2 -> {
                    switchTab(1)
                }
                com.boyu.emove.R.id.item_3 -> {
                    switchTab(2)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun switchTab(index: Int){
        when(index) {
            0 -> {
                if(homeFragment == null){
                    homeFragment = HomeFragment.newInstance()
                    fragmentList[0] = homeFragment!!
                }
            }
            1 -> {
                if(infoFragment == null){
                    infoFragment = InfoFragment.newInstance()
                    fragmentList[1] = infoFragment!!
                }
            }
            2 -> {
                if(orderListFragment == null){
                    orderListFragment = OrderListFragment.newInstance()
                    fragmentList[2] = orderListFragment!!
                }
            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        var toFragment = fragmentList[index]

        if(seletedFragment == null) {
            transaction.add(R.id.fl_container, toFragment!!).commit()
            seletedFragment = toFragment
            return
        }

        if(!toFragment?.isAdded!!) {
            transaction.add(R.id.fl_container, toFragment!!)
        }
        transaction.hide(seletedFragment!!).show(toFragment!!).commit()
        seletedFragment = toFragment

    }
}
