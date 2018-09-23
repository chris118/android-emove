package com.boyu.emove.vehicle.interactor

import com.boyu.emove.Infoex.entity.InfoExBody
import com.boyu.emove.Infoex.repository.InfoexRepository
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.Movein
import com.boyu.emove.info.entity.Moveout
import com.boyu.emove.info.repository.InfoRepository
import com.boyu.emove.vehicle.repository.VehicleRepository
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