package com.boyu.emove.extension

import android.text.Editable

/**
 * Created by chrisw on 2018/9/19.
 */
fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
