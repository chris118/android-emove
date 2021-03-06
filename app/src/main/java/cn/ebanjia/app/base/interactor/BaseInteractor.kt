package cn.ebanjia.app.base.interactor

import cn.ebanjia.app.vendor.UI
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by chrisw on 2018/9/6.
 */
abstract class BaseInteractor<in Params, out Type> where Type: Any {

    abstract suspend fun run(params: Params): Type

    //When you specify an invoke operator on a class, it can be called on
    // any instances of the class without a method name!
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