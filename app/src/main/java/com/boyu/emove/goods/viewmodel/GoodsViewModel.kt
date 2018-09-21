package com.boyu.emove.goods.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.goods.entity.CartGood
import com.boyu.emove.goods.entity.CartGoodBody
import com.boyu.emove.goods.entity.Goods
import com.boyu.emove.goods.interactor.GetGoodsInteractor
import com.boyu.emove.goods.interactor.UpdateGoodsInteractor
import javax.inject.Inject

class GoodsViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var getGoodsInteractor: GetGoodsInteractor

    @Inject
    lateinit var updateGoodsInteractor: UpdateGoodsInteractor

    var getGoodsResponse: MutableLiveData<BaseResponse<Goods>> = MutableLiveData()
    var updateGoodsResponse: MutableLiveData<BaseResponse<String>> = MutableLiveData()

    fun getGoods() {
        getGoodsInteractor(BaseInteractor.None()){
            (this@GoodsViewModel).getGoodsResponse.value = it
        }
    }

    fun updateGoods(cartGoods: CartGoodBody) {
        updateGoodsInteractor(cartGoods){
            (this@GoodsViewModel).updateGoodsResponse.value = it
        }
    }
}