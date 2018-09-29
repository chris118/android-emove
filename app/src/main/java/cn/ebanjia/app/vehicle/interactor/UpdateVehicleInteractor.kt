package cn.ebanjia.app.vehicle.interactor

import cn.ebanjia.app.Infoex.entity.InfoExBody
import cn.ebanjia.app.Infoex.repository.InfoexRepository
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.info.entity.Movein
import cn.ebanjia.app.info.entity.Moveout
import cn.ebanjia.app.info.repository.InfoRepository
import cn.ebanjia.app.vehicle.repository.VehicleRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/19.
 */
class UpdateVehicleInteractor @Inject constructor(private val repository: VehicleRepository)
    : BaseInteractor<Int, BaseResponse<String>>() {
    override suspend fun run(params: Int): BaseResponse<String> {
        return repository.updateVehicle(params)!!
    }
}