package com.boyu.emove.orderlist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.boyu.emove.R

class OrderListFragment : Fragment() {

    companion object {
        fun newInstance() = OrderListFragment()
    }

    private lateinit var viewModel: OrderListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.order_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OrderListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}