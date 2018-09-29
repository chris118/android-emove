package cn.ebanjia.app.goods.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.goods.entity.CartGood
import cn.ebanjia.app.goods.entity.CartGoodBody
import cn.ebanjia.app.goods.entity.Goods
import cn.ebanjia.app.goods.interactor.GetGoodsInteractor
import cn.ebanjia.app.goods.interactor.UpdateGoodsInteractor
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