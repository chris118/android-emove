package com.boyu.emove.info

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.boyu.emove.R
import kotlinx.android.synthetic.main.info_fragment.*
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    private lateinit var viewModel: InfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(InfoViewModel::class.java)

        btn_go.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_infoFragment_to_goodsFragment))
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_item_next, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.goodsFragment -> {

                val navi =  Navigation.findNavController(activity!!, R.id.nav_host_fragment)
                navi.navigate(R.id.action_infoFragment_to_goodsFragment)

//                NavigationUI.onNavDestinationSelected(item,
//                        Navigation.findNavController(activity!!, R.id.nav_host_fragment))
//                        || super.onOptionsItemSelected(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
