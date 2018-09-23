package com.boyu.emove.di

import com.boyu.emove.AndroidApplication
import com.boyu.emove.Infoex.ui.InfoExFragment
import com.boyu.emove.login.ui.LoginActivity
import com.boyu.emove.goods.ui.GoodsFragment
import com.boyu.emove.info.ui.AddressActivity
import com.boyu.emove.info.ui.InfoFragment
import com.boyu.emove.order.ui.OrderFragment
import com.boyu.emove.orderlist.ui.OrderListFragment
import com.boyu.emove.vehicle.ui.VehicleFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by chrisw on 2018/8/31.
 */

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(loginActivity: LoginActivity)
    fun inject(infoFragment: InfoFragment)
    fun inject(addressActivity: AddressActivity)
    fun inject(goodsFragment: GoodsFragment)
    fun inject(infoExFragment: InfoExFragment)
    fun inject(vehicleFragment: VehicleFragment)
    fun inject(orderFragment: OrderFragment)
    fun inject(orderListFragment: OrderListFragment)

}