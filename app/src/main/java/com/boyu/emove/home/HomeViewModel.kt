package com.boyu.emove.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class HomeViewModel : ViewModel() {
    val counter: MutableLiveData<Int> = MutableLiveData()

    init {
        counter.value = 1
    }

    fun increment() {
        counter.value = counter.value?.plus(1)
    }
}
