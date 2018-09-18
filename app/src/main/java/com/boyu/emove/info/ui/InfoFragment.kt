package com.boyu.emove.info.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.pickerview.OptionsPickerView
import com.bigkoo.pickerview.OptionsPickerView.OnOptionsSelectListener
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.base.ui.DividerItemDecoration
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.info.entity.Movein
import com.boyu.emove.info.entity.Moveout
import com.boyu.emove.info.ui.adapter.InfoAdapter
import com.boyu.emove.info.viewmodel.InfoViewModel
import kotlinx.android.synthetic.main.fragment_info.*
import javax.inject.Inject

class InfoFragment : BaseNaviFragment() {
    private val TAG = InfoFragment::class.java.simpleName

    companion object {
        fun newInstance() = InfoFragment()
    }
    private val elevatorOptions = arrayOf("有", "无")
    private val floorsOptions = arrayOf("1楼", "2楼", "3楼", "4楼", "5楼", "6楼", "7楼", "8楼")
    private val assembleOptions = arrayOf("需要", "不需要")

    @Inject lateinit var infoAdapter: InfoAdapter
    private var viewModel: InfoViewModel? = null
    private lateinit var movein: Movein
    private lateinit var moveout: Moveout
    private lateinit var outElevatorPicker: OptionsPickerView<String>
    private lateinit var outFloorPicker: OptionsPickerView<String>
    private lateinit var outAssemblePicker: OptionsPickerView<String>
    private lateinit var inElevatorPicker: OptionsPickerView<String>
    private lateinit var inFloorPicker: OptionsPickerView<String>
    private lateinit var inAssemblePicker: OptionsPickerView<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {
            this.getInfoResponse.observe(this@InfoFragment, Observer {
               if(it.code == 0){
                   movein = it.result.movein
                   moveout = it.result.moveout
                   infoAdapter.data = Pair(it.result.moveout, it.result.movein)
               }
            })
        }
        initializeView()
        loadData()
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_infoFragment_to_goodsFragment
    }

    private fun loadData() {
        viewModel?.getInfo()
    }

    private fun initializeView() {
        recycleView.layoutManager = LinearLayoutManager(activity)
        recycleView.adapter = infoAdapter
        recycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))
        infoAdapter.clickListener = {
            if (moveout.is_elevator == 0){
                if(it == 2) {
                    outElevatorPicker?.show()
                }
                if(it == 3) {
                    outFloorPicker?.show()
                }
                if(it == 4) {
                    outAssemblePicker?.show()
                }
                if(it == 8) {
                    inElevatorPicker?.show()
                }
                if(it == 9) {
                    inFloorPicker?.show()
                }
                if(it == 10) {
                    inAssemblePicker?.show()
                }
            }else {
                if(it == 2) {
                    outElevatorPicker?.show()
                }
                if(it == 3) {
                    outAssemblePicker?.show()
                }
                if(it == 7) {
                    inElevatorPicker?.show()
                }
                if(it == 8) {
                    inFloorPicker?.show()
                }
                if(it == 9) {
                    inAssemblePicker?.show()
                }
            }

        }
        infoAdapter.distanceChanged = { distance, n ->
            if(distance.length > 0) {
                if(n == 0){
                    moveout.distance_meter = distance.toInt()
                }else {
                    movein.distance_meter = distance.toInt()
                }
            }
        }

        // out elevator
        outElevatorPicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    if (option1 == 0){
                        moveout.is_elevator = 1
                    }else {
                        moveout.is_elevator = 0
                    }
                    infoAdapter.data = Pair(moveout, movein)
                }).build() as OptionsPickerView<String>
        outElevatorPicker.setPicker(elevatorOptions.toMutableList())

        // out floor
        outFloorPicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    moveout.floor = option1 + 1
                    infoAdapter.data = Pair(moveout, movein)
                }).build() as OptionsPickerView<String>
        outFloorPicker.setPicker(floorsOptions.toMutableList())

        // out assemble
        outAssemblePicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    if (option1 == 0){
                        moveout.is_handling = 1
                    }else {
                        moveout.is_handling = 0
                    }
                    infoAdapter.data = Pair(moveout, movein)
                }).build() as OptionsPickerView<String>
        outAssemblePicker.setPicker(assembleOptions.toMutableList())

        // in elevator
        inElevatorPicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    if (option1 == 0){
                        movein.is_elevator = 1
                    }else {
                        movein.is_elevator = 0
                    }
                    infoAdapter.data = Pair(moveout, movein)
                }).build() as OptionsPickerView<String>
        inElevatorPicker.setPicker(elevatorOptions.toMutableList())

        // in floor
        inFloorPicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    movein.floor = option1 + 1
                    infoAdapter.data = Pair(moveout, movein)
                }).build() as OptionsPickerView<String>
        inFloorPicker.setPicker(floorsOptions.toMutableList())

        // in assemble
        inAssemblePicker = OptionsPickerView.Builder(activity,
                OnOptionsSelectListener{ option1 : Int, option2 : Int, option3 : Int, v: View? ->
                    if (option1 == 0){
                        movein.is_handling = 1
                    }else {
                        movein.is_handling = 0
                    }
                    infoAdapter.data = Pair(moveout, movein)
                }).build() as OptionsPickerView<String>
        inAssemblePicker.setPicker(assembleOptions.toMutableList())
    }
}
