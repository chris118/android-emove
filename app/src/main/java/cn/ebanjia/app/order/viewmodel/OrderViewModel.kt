package cn.ebanjia.app.order.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.order.entity.Order
import cn.ebanjia.app.order.entity.OrderSave
import cn.ebanjia.app.order.interactor.GetOrderInteractor
import cn.ebanjia.app.order.interactor.GetOrderWithIDInteractor
import cn.ebanjia.app.order.interactor.SaveOrderInteractor
import javax.inject.Inject

class OrderViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var getOrderInteractor: GetOrderInteractor

    var getOrderResponse: MutableLiveData<BaseResponse<Order>> = MutableLiveData()

    @Inject
    lateinit var saveOrderInteractor: SaveOrderInteractor

    var saveOrderResponse: MutableLiveData<BaseResponse<OrderSave>> = MutableLiveData()


    @Inject
    lateinit var getOrderWithIDInteractor: GetOrderWithIDInteractor


    fun getOrder() {
        getOrderInteractor(BaseInteractor.None()) {
            (this@OrderViewModel).getOrderResponse.value = it
        }
    }

    fun saveOrder() {
        saveOrderInteractor(BaseInteractor.None()) {
            (this@OrderViewModel).saveOrderResponse.value = it
        }
    }

    fun getOrderWithId(order_id: Int) {
        getOrderWithIDInteractor(order_id) {
            (this@OrderViewModel).getOrderResponse.value = it
        }
    }

}