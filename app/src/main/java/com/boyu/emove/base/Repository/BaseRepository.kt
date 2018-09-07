package com.boyu.emove.base.Repository

/**
 * Created by chrisw on 2018/9/6.
 */
import retrofit2.Call

open class BaseRepository {
    protected fun<T> request(call: Call<T>, callback: (T) -> T) : T{
        val response = call.execute()
        return callback(response.body()!!)
    }
}