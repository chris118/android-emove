package cn.ebanjia.app.vehicle.repository

import cn.ebanjia.app.Infoex.entity.InfoEx
import cn.ebanjia.app.Infoex.entity.InfoExBody
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.api.EmoveService
import cn.ebanjia.app.base.Repository.BaseRepository
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.entity.Movein
import cn.ebanjia.app.info.entity.Moveout
import cn.ebanjia.app.vehicle.entity.VehicleInfo
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class VehicleRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun getVehicleInfo(order_by_field: String): BaseResponse<VehicleInfo>? {
        return request(service.getVehicleInfo(order_by_field))
    }

    fun updateVehicle(body: Int): BaseResponse<String>? {
        return request(service.updateVehicleInfo(body))
    }
}