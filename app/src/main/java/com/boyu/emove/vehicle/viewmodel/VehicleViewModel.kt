package com.boyu.emove.vehicle.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.vehicle.entity.VehicleInfo
import com.boyu.emove.vehicle.interactor.GetVehicleInteractor
import javax.inject.Inject

class VehicleViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var getVehicleInteractor: GetVehicleInteractor

    var getVehicleResponse: MutableLiveData<BaseResponse<VehicleInfo>> = MutableLiveData()

    fun getVehicle(order_by_field: String) {
        getVehicleInteractor(order_by_field) {
            (this@VehicleViewModel).getVehicleResponse.value = it
        }
    }
}