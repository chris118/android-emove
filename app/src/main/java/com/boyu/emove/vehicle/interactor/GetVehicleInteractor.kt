package com.boyu.emove.vehicle.interactor

import com.boyu.emove.Infoex.entity.InfoEx
import com.boyu.emove.Infoex.repository.InfoexRepository
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.repository.InfoRepository
import com.boyu.emove.vehicle.entity.VehicleInfo
import com.boyu.emove.vehicle.repository.VehicleRepository
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