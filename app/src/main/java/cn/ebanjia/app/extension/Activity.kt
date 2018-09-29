package cn.ebanjia.app.extension

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProvider.Factory

/**
 * Created by chrisw on 2018/9/9.
 */

inline fun <reified T : ViewModel> AppCompatActivity.createViewModel(factory: Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}