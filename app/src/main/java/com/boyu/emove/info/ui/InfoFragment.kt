package com.boyu.emove.info.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.base.ui.DividerItemDecoration
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.info.ui.adapter.InfoAdapter
import com.boyu.emove.info.viewmodel.InfoViewModel
import kotlinx.android.synthetic.main.fragment_info.*
import javax.inject.Inject

class InfoFragment : BaseNaviFragment() {
    companion object {
        fun newInstance() = InfoFragment()
    }

    @Inject lateinit var infoAdapter: InfoAdapter

    private var viewModel: InfoViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory){

        }

        initializeView()
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_infoFragment_to_goodsFragment
    }

    private fun initializeView() {
        recycleView.layoutManager = LinearLayoutManager(activity)
        recycleView.adapter = infoAdapter
        recycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))
    }
}
