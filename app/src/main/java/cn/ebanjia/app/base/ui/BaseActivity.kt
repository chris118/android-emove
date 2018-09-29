package cn.ebanjia.app.base.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.ebanjia.app.AndroidApplication
import cn.ebanjia.app.di.ApplicationComponent
import javax.inject.Inject

/**
 * Created by chrisw on 2018/8/23.
 */
open class BaseActivity: AppCompatActivity() {
    val appComponent : ApplicationComponent by lazy{
        (this.application as AndroidApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun<reified T: Activity> startActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }
}