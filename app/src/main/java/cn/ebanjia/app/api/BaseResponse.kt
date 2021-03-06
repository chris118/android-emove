package cn.ebanjia.app.api

/**
 * Created by chrisw on 2018/9/5.
 */
data class BaseResponse<T>(var code: Int, var msg: String, var result: T) {
    companion object {
        fun empty() = BaseResponse(-1, "", null)
    }
}

data class BaseResponse2(var code: Int, var msg: String)