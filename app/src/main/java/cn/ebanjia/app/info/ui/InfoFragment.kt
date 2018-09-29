package cn.ebanjia.app.info.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import cn.ebanjia.app.R
import cn.ebanjia.app.base.ui.BaseNaviFragment
import cn.ebanjia.app.extension.createViewModel
import cn.ebanjia.app.extension.toast
import cn.ebanjia.app.info.viewmodel.InfoViewModel
import cn.ebanjia.app.utils.SharedPreferencesUtil
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : BaseNaviFragment() {

    private val TAG = InfoFragment::class.java.simpleName
    private var normalFragment: NormalFragment? = null
    private var advanceFragment: NormalFragment? = null
    private var companyFragment: CompanyFragment? = null


    private var viewModel: InfoViewModel? = null
    private var is_compay_banjia = false
    private var pagerAdapter: FragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {
            this.getInfoResponse.observe(this@InfoFragment, Observer {
                if(it.code == 0){
                    if(tl_tabs.selectedTabPosition == 0) {
                        normalFragment?.movein = it.result.movein
                        normalFragment?.moveout = it.result.moveout
                        normalFragment?.refreshData()
                    }else {
                        advanceFragment?.movein = it.result.movein
                        advanceFragment?.moveout = it.result.moveout
                        advanceFragment?.refreshData()
                    }
                }else {
                    it.msg.toast(activity!!)
                }
            })

            this.updateInfoResponse.observe(this@InfoFragment, Observer {
                if (it.code == 0){
                    goNext()
                }else {
                    it.msg.toast(activity!!)
                }
            })

            this.saveCompanyResponse.observe(this@InfoFragment, Observer {
                if (it.code == 0){
                    AlertDialog.Builder(activity!!)
                            .setMessage("预约成功! 稍后将有我司专员与您联系.")
                            .setTitle("")
                            .setPositiveButton("确定") { _, _ ->

                            }
                            .create()
                            .show()
                }else {
                    it.msg.toast(activity!!)
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var banjia_type by SharedPreferencesUtil(activity!!,"banjia_type","1")
        banjia_type = "1"

        initializeView()
        loadData()
    }

    override fun onResume() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity!!.bnv_bottom_navigation.visibility = View.VISIBLE
        super.onResume()
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        menu?.getItem(0)?.isVisible = !is_compay_banjia
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_infoFragment_to_goodsFragment
    }

    override fun onNext() {
        if(tl_tabs.selectedTabPosition == 0) {
            normalFragment?.let {
                viewModel?.updateInfo(it.moveout, it.movein)
            }
        }else {
            advanceFragment?.let {
                viewModel?.updateInfo(it.moveout, it.movein)
            }
        }
    }
    private fun loadData() {
        viewModel?.getInfo()
    }

    private fun initializeView() {
         pagerAdapter = object : FragmentPagerAdapter(this.childFragmentManager) {
            override fun getItem(position: Int): Fragment? {

                when(position){
                    0-> {
                        normalFragment = NormalFragment()
                        normalFragment?.fragmentType = NormalFragment.FragmentEnum.normal
                        return normalFragment
                    }
                    1-> {
                        advanceFragment = NormalFragment()
                        advanceFragment?.fragmentType = NormalFragment.FragmentEnum.advance
                        return advanceFragment
                    }
                    else-> {
                        companyFragment = CompanyFragment()
                        companyFragment?.saveCompanyOrder = { name, contact, mobile ->
                            viewModel?.saveCompany(name, contact, mobile, "")
                        }
                        return companyFragment
                    }
                }
            }

            override fun getCount(): Int {
                return 3
            }

             override fun getPageTitle(position: Int): CharSequence? {
                 return when(position){
                     0 -> "普通搬家"
                     1 -> "精致搬家"
                     2 -> "企业搬家"
                     else -> {
                         ""
                     }
                 }
             }
        }

        pv_container.adapter = pagerAdapter
        tl_tabs.setupWithViewPager(pv_container)

        //tab layout change event
        tl_tabs.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0){
                    is_compay_banjia = false
                    var banjia_type by SharedPreferencesUtil(activity!!,"banjia_type","1")
                    banjia_type = "1"
                    loadData()
                }else if (tab.position == 1){
                    is_compay_banjia = false
                    var banjia_type by SharedPreferencesUtil(activity!!,"banjia_type","1")
                    banjia_type = "2"
                    loadData()
                }
                else if (tab.position == 2) {
                    is_compay_banjia = true
                }
                activity?.invalidateOptionsMenu()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }
}
