package com.boyu.emove.Infoex.entity

data class InfoEx(
    val time_slot: List<TimeSlot>,
    var cart_time: CartTime,
    var cart_contacts: CartContacts
)

data class CartTime(
    var year: String,
    var month: String,
    var day: String,
    var time_slot_id: String
)

data class CartContacts(
    var user_name: String,
    var user_telephone: String,
    var user_note: String,
    var is_invoice: Int
)

data class TimeSlot(
    var time_slot_id: Int,
    var title: String
)

data class InfoExBody(
        var year: Int,
        var month: Int,
        var day: Int,
        var time_slot_id: Int,
        var user_name: String,
        var user_telephone: String,
        var is_invoice: Int,
        var user_note: String
)