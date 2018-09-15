package com.boyu.emove.info.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.info.viewmodel.InfoViewModel

class InfoFragment : BaseNaviFragment() {
    companion object {
        fun newInstance() = InfoFragment()
    }

    private var viewModel: InfoViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory){

        }
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_infoFragment_to_goodsFragment
    }
}
