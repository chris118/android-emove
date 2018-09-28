package com.boyu.emove.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.boyu.emove.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private val TAG = HomeFragment::class.java.simpleName
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.invalidateOptionsMenu()

        iv_book.setOnClickListener {
//            (activity as? MainActivity)?.showActionBar()
            activity?.let {
                it.bnv_bottom_navigation.selectedItemId = R.id.infoFragment
                //显示底部导航
                it.bnv_bottom_navigation?.visibility = View.VISIBLE
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        menu?.getItem(0)?.isVisible = false
    }
}
