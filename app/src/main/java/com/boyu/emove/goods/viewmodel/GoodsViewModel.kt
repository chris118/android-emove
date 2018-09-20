package com.boyu.emove.goods.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.goods.entity.Goods
import com.boyu.emove.goods.interactor.GetGoodsInteractor
import javax.inject.Inject

class GoodsViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var getGoodsInteractor: GetGoodsInteractor


    var getGoodsResponse: MutableLiveData<BaseResponse<Goods>> = MutableLiveData()

    fun getGoods() {
        getGoodsInteractor(BaseInteractor.None()){
            (this@GoodsViewModel).getGoodsResponse.value = it
        }
    }
}