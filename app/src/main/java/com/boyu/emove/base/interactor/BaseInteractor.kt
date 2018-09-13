package com.boyu.emove.base.interactor

import com.boyu.emove.vendor.UI
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by chrisw on 2018/9/6.
 */
abstract class BaseInteractor<in Params, out Type> where Type: Any {

    abstract suspend fun run(params: Params): Type

    //操作符重载 实例化时调用
    operator fun invoke(params: Params, onResult:(result: Type) -> Unit = {}) {
        val job = async(CommonPool) {
            run(params)
        }


        launch(UI) {
            onResult(job.await())
        }
    }

    class None
}