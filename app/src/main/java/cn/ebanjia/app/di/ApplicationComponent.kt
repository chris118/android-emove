package cn.ebanjia.app.di

import cn.ebanjia.app.AndroidApplication
import cn.ebanjia.app.Infoex.ui.InfoExFragment
import cn.ebanjia.app.login.ui.LoginActivity
import cn.ebanjia.app.goods.ui.GoodsFragment
import cn.ebanjia.app.info.ui.AddressActivity
import cn.ebanjia.app.info.ui.InfoFragment
import cn.ebanjia.app.order.ui.OrderFragment
import cn.ebanjia.app.orderlist.ui.OrderListFragment
import cn.ebanjia.app.vehicle.ui.VehicleFragment
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