package com.boyu.emove.order.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.order.entity.Order
import com.boyu.emove.order.entity.OrderSave
import com.boyu.emove.order.interactor.GetOrderInteractor
import com.boyu.emove.order.interactor.GetOrderWithIDInteractor
import com.boyu.emove.order.interactor.SaveOrderInteractor
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