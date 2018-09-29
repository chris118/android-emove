package cn.ebanjia.app.vehicle.interactor

import cn.ebanjia.app.Infoex.entity.InfoEx
import cn.ebanjia.app.Infoex.repository.InfoexRepository
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.repository.InfoRepository
import cn.ebanjia.app.vehicle.entity.VehicleInfo
import cn.ebanjia.app.vehicle.repository.VehicleRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/15.
 */
class GetVehicleInteractor @Inject constructor(private val repository: VehicleRepository)
    : BaseInteractor<String, BaseResponse<VehicleInfo>>() {
    override suspend fun run(params: String): BaseResponse<VehicleInfo> {
        return repository.getVehicleInfo(params)!!
    }
}