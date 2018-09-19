package com.boyu.emove.info.entity

import com.boyu.emove.api.BaseResponse

/**
 * Created by chrisw on 2018/9/18.
 */
data class Info(
    val moveout: Moveout,
    val movein: Movein
)

data class Moveout (
    var address: String,
    var floor: Int,
    var is_elevator: Int,
    var is_handling: Int,
    var distance_meter: Int,
    var lat: Double,
    var lng: Double,
    var uid: String
) {
    companion object {
        fun empty() = Moveout("",0,0,0,0,0.0,0.0,"")
    }
}

data class Movein(
    var address: String,
    var floor: Int,
    var is_elevator: Int,
    var is_handling: Int,
    var distance_meter: Int,
    var lat: Double,
    var lng: Double,
    var uid: String
) {
    companion object {
        fun empty() = Movein("",0,0,0,0,0.0,0.0,"")
    }
}