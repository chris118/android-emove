package com.boyu.emove.info.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.baidu.mapapi.search.core.PoiInfo
import com.baidu.mapapi.search.poi.*
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseActivity
import com.boyu.emove.base.ui.DividerItemDecoration
import com.boyu.emove.info.ui.adapter.AddressAdapter
import kotlinx.android.synthetic.main.activity_adress.*
import javax.inject.Inject

class AddressActivity : BaseActivity() {
    private val TAG = AddressActivity::class.java.simpleName

    @Inject lateinit var addressAdapter: AddressAdapter

    private val pioSearch =  PoiSearch.newInstance()
    private var results: List<PoiInfo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adress)

        appComponent.inject(this)

        initBaiduMap()
        initializeView()
    }

    private fun initializeView() {

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = addressAdapter
        recycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        addressAdapter.clickListener = {
            Log.d(TAG, this@AddressActivity.results[it].address)
            Log.d(TAG, this@AddressActivity.results[it].uid)

            var intent = Intent()
            intent.putExtra("address_name", this@AddressActivity.results[it].address)
            intent.putExtra("address_uid", this@AddressActivity.results[it].uid)

            setResult(10001, intent)

            finish()
        }

        tv_map_cancel.setOnClickListener {
            finish()
        }

        tv_keyword.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                var poiCitySearchOption = PoiCitySearchOption()
                poiCitySearchOption.keyword(p0.toString()).mCity = "上海"
                pioSearch.searchInCity(poiCitySearchOption)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
    }

    private fun initBaiduMap() {

        pioSearch.setOnGetPoiSearchResultListener(object : OnGetPoiSearchResultListener {
            override fun onGetPoiIndoorResult(p0: PoiIndoorResult?) {
            }

            override fun onGetPoiResult(p0: PoiResult?) {
                if (p0 != null){
                    if (p0.allPoi != null){
                        this@AddressActivity.addressAdapter.data = p0.allPoi
                        results = p0.allPoi
                    }
                }
            }

            override fun onGetPoiDetailResult(p0: PoiDetailResult?) {
            }

            override fun onGetPoiDetailResult(p0: PoiDetailSearchResult?) {
            }
        } )
    }
}
