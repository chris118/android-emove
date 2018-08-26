package com.boyu.emove.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Created by chrisw on 2018/8/21.
 */
class MainViewModel: ViewModel() {
    val counter: MutableLiveData<Int> = MutableLiveData()

    init {
        counter.value = 1
    }

    fun increment() {
        counter.value = counter.value?.plus(1)
    }
}