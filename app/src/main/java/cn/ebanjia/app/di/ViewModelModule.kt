/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ebanjia.app.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cn.ebanjia.app.Infoex.viewmodel.InfoexViewModel
import cn.ebanjia.app.login.viewmodel.LoginViewModel
import cn.ebanjia.app.goods.viewmodel.GoodsViewModel
import cn.ebanjia.app.info.viewmodel.InfoViewModel
import cn.ebanjia.app.order.viewmodel.OrderViewModel
import cn.ebanjia.app.orderlist.viewmodel.OrderListViewModel
import cn.ebanjia.app.vehicle.viewmodel.VehicleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindsLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfoViewModel::class)
    abstract fun bindsInfoViewModel(infoViewModel: InfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GoodsViewModel::class)
    abstract fun bindsGoodsViewModel(goodsViewModel: GoodsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfoexViewModel::class)
    abstract fun bindsInfoexViewModel(infoexViewModel: InfoexViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VehicleViewModel::class)
    abstract fun bindsVehicleViewModel(vehicleViewModel: VehicleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    abstract fun bindsOrderViewModel(orderViewModel: OrderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderListViewModel::class)
    abstract fun bindsOrderListViewModel(orderListViewModel: OrderListViewModel): ViewModel
}