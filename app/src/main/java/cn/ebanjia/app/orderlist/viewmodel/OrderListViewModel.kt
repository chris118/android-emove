package cn.ebanjia.app.orderlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.orderlist.entity.OrderList
import cn.ebanjia.app.orderlist.interactor.GetOrderListInteractor
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
