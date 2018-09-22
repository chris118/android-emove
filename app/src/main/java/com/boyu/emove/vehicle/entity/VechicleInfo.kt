package com.boyu.emove.vehicle.entity

data class VehicleInfo(
    val usable_fleet: List<UsableFleet>,
    val move_date: String,
    val selected_fleet_id: Int
)

data class UsableFleet(
    val fleet_id: Int,
    val fleet_name: String,
    val fleet_telephone: String,
    val fleet_address: String,
    val distance_kilometer: Double,
    val evaluate_star: Int,
    val evaluate_count: Int,
    val order_count: Int,
    val remainder: Int,
    val discount: Int
)