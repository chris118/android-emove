package com.boyu.emove.orderlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.orderlist.entity.OrderList
import com.boyu.emove.orderlist.interactor.GetOrderListInteractor
import javax.inject.Inject

class OrderListViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var getOrderListInteractor: GetOrderListInteractor

    var getOrderListResponse: MutableLiveData<BaseResponse<List<OrderList>>> = MutableLiveData()

    fun getOrderList(page: Int, order_status: String) {
        getOrderListInteractor(Pair(page, order_status)) {
            (this@OrderListViewModel).getOrderListResponse.value = it
        }
    }
}
