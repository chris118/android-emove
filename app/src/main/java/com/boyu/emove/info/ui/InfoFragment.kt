package com.boyu.emove.info.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bigkoo.pickerview.OptionsPickerView
import com.bigkoo.pickerview.OptionsPickerView.OnOptionsSelectListener
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.extension.toEditable
import com.boyu.emove.info.entity.Movein
import com.boyu.emove.info.entity.Moveout
import com.boyu.emove.info.viewmodel.InfoViewModel
import kotlinx.android.synthetic.main.fragment_info.*
import com.baidu.mapsdkplatform.comapi.map.v
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.boyu.emove.utils.KeyboardktUtils


class InfoFragment : BaseNaviFragment() {

    private val TAG = InfoFragment::class.java.simpleName

    companion object {
        fun newInstance() = InfoFragment()
    }
    private val elevatorOptions = arrayOf("有", "无")
    private val floorsOptions = arrayOf("1楼", "2楼", "3楼", "4楼", "5楼", "6楼", "7楼", "8楼")
    private val assembleOptions = arrayOf("需要", "不需要")

    private var viewModel: InfoViewModel? = null
    private lateinit var moveout: Moveout
    private lateinit var movein: Movein
    private lateinit var outElevatorPicker: OptionsPickerView<String>
    private lateinit var outFloorPicker: OptionsPickerView<String>
    private lateinit var outAssemblePicker: OptionsPickerView<String>
    private lateinit var inElevatorPicker: OptionsPickerView<String>
    private lateinit var inFloorPicker: OptionsPickerView<String>
    private lateinit var inAssemblePicker: OptionsPickerView<String>
    private var address_type = 0 // 0: out  1: in

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {
            this.getInfoResponse.observe(this@InfoFragment, Observer {
                if(it.code == 0){
                    movein = it.result.movein
                    moveout = it.result.moveout

                    //fix bug
                    if(movein.floor == 0){
                        movein.floor = 1
                    }
                    if(moveout.floor == 0){
                        moveout.floor = 1
                    }
                    this@InfoFragment.refreshData()
                }
            })

            this.updateInfoResponse.observe(this@InfoFragment, Observer {
                if (it.code == 0){
                    goNext()
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeView()
        loadData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10000 && resultCode == 10001) {
            if(address_type == 0){
                moveout.address =  data?.getStringExtra("address_name") ?: ""
                moveout.uid = data?.getStringExtra("address_uid") ?: ""
                tv_out_address.text = moveout.address
            }else {
                movein.address =  data?.getStringExtra("address_name") ?: ""
                movein.uid = data?.getStringExtra("address_uid") ?: ""
                tv_in_address.text = movein.address
            }
        }
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_infoFragment_to_goodsFragment
    }

    override fun onNext() {
        viewModel?.updateInfo(moveout, movein)
    }
    private fun loadData() {
        viewModel?.getInfo()
    }

    private fun refreshData() {
        //out
        tv_out_address.text = moveout.address
        if (moveout.is_elevator == 1){
            ll_out_floor.visibility = GONE
            tv_out_elevator.text = "有"
        }else {
            tv_out_elevator.text = "无"
            ll_out_floor.visibility = VISIBLE
        }
        tv_out_floor.text = floorsOptions[moveout.floor - 1]
        if (moveout.is_handling == 1){
            tv_out_assemble.text = "需要"
        }else {
            tv_out_assemble.text = "不需要"
        }
        if ( moveout.distance_meter > 0){
            tv_out_distance.text = moveout.distance_meter.toString().toEditable()
        }

        //in
        tv_in_address.text = movein.address
        if (movein.is_elevator == 1){
            ll_in_floor.visibility = GONE
            tv_in_elevator.text = "有"
        }else {
            tv_in_elevator.text = "无"
            ll_in_floor.visibility = VISIBLE
        }
        tv_in_floor.text = floorsOptions[movein.floor - 1]
        if (movein.is_handling == 1){
            tv_in_assemble.text = "需要"
        }else {
            tv_in_assemble.text = "不需要"
        }
        if( movein.distance_meter > 0){
            tv_in_distance.text = movein.distance_meter.toString().toEditable()
        }
    }

    private fun initializeView() {
        ll_c_info.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
        }

        //搬出
        tv_out_address.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            address_type = 0
            val intent = Intent(activity, AddressActivity::class.java)
            startActivityForResult(intent, 10000)
        }
        ll_out_elevator.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            outElevatorPicker?.show()
        }
        ll_out_floor.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            outFloorPicker?.show()
        }
        ll_out_assemble.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            outAssemblePicker?.show()
        }
        tv_out_distance.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().length > 0){
                    try {
                        moveout.distance_meter = p0.toString().toInt()
                    }catch (e: Exception){

                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        //搬入
        tv_in_address.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            address_type = 1
            val intent = Intent(activity, AddressActivity::class.java)
            startActivityForResult(intent, 10000)
        }
        ll_in_elevator.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            inElevatorPicker?.show()
        }
        ll_in_floor.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            inFloorPicker?.show()
        }
        ll_in_assemble.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            inAssemblePicker?.show()
        }
        tv_in_distance.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().length > 0){
                    try {
                        movein.distance_meter = p0.toString().toInt()
                    }catch (e: Exception){

                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        // out elevator
        outElevatorPicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    if (option1 == 0){
                        moveout.is_elevator = 1
                        ll_out_floor.visibility = GONE
                        tv_out_elevator.text = "有"
                    }else {
                        moveout.is_elevator = 0
                        tv_out_elevator.text = "无"
                        ll_out_floor.visibility = VISIBLE
                    }
                }).build() as OptionsPickerView<String>
        outElevatorPicker.setPicker(elevatorOptions.toMutableList())

        // out floor
        outFloorPicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    moveout.floor = option1 + 1
                    tv_out_floor.text = floorsOptions[moveout.floor - 1]

                }).build() as OptionsPickerView<String>
        outFloorPicker.setPicker(floorsOptions.toMutableList())

        // out assemble
        outAssemblePicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    if (option1 == 0){
                        moveout.is_handling = 1
                        tv_out_assemble.text = "需要"
                    }else {
                        moveout.is_handling = 0
                        tv_out_assemble.text = "不需要"
                    }
                }).build() as OptionsPickerView<String>
        outAssemblePicker.setPicker(assembleOptions.toMutableList())

        // in elevator
        inElevatorPicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    if (option1 == 0){
                        movein.is_elevator = 1
                        ll_in_floor.visibility = GONE
                        tv_in_elevator.text = "有"
                    }else {
                        movein.is_elevator = 0
                        ll_in_floor.visibility = VISIBLE
                        tv_in_elevator.text = "无"
                    }
                }).build() as OptionsPickerView<String>
        inElevatorPicker.setPicker(elevatorOptions.toMutableList())

        // in floor
        inFloorPicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    movein.floor = option1 + 1
                    tv_in_floor.text = floorsOptions[movein.floor - 1]
                }).build() as OptionsPickerView<String>
        inFloorPicker.setPicker(floorsOptions.toMutableList())

        // in assemble
        inAssemblePicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    if (option1 == 0){
                        movein.is_handling = 1
                        tv_in_assemble.text = "需要"
                    }else {
                        movein.is_handling = 0
                        tv_in_assemble.text = "不需要"
                    }
                }).build() as OptionsPickerView<String>
        inAssemblePicker.setPicker(assembleOptions.toMutableList())
    }
}
