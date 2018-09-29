package cn.ebanjia.app.info.ui


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigkoo.pickerview.OptionsPickerView
import cn.ebanjia.app.R
import cn.ebanjia.app.base.ui.BaseFragment
import cn.ebanjia.app.extension.toEditable
import cn.ebanjia.app.info.entity.Movein
import cn.ebanjia.app.info.entity.Moveout
import cn.ebanjia.app.utils.KeyboardktUtils
import kotlinx.android.synthetic.main.fragment_normal.*

class NormalFragment : BaseFragment() {
    enum class FragmentEnum {
        normal,
        advance
    }
    var moveout: Moveout = Moveout.empty()
    var movein: Movein = Movein.empty()
    var fragmentType: FragmentEnum = FragmentEnum.normal

    private val elevatorOptions = arrayOf("有", "无")
    private val floorsOptions = arrayOf("1楼", "2楼", "3楼", "4楼", "5楼", "6楼", "7楼", "8楼")
    private val assembleOptions = arrayOf("需要", "不需要")
    private lateinit var outElevatorPicker: OptionsPickerView<String>
    private lateinit var outFloorPicker: OptionsPickerView<String>
    private lateinit var outAssemblePicker: OptionsPickerView<String>
    private lateinit var inElevatorPicker: OptionsPickerView<String>
    private lateinit var inFloorPicker: OptionsPickerView<String>
    private lateinit var inAssemblePicker: OptionsPickerView<String>
    private var address_type = 0 // 0: out  1: in

    override fun getTargetLayoutId(): Int {
        return 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_normal, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10000 && resultCode == 10001) {
            if(address_type == 0){
                moveout.address =  data?.getStringExtra("address_name") ?: ""
                moveout.uid = data?.getStringExtra("address_uid") ?: ""
                tv_out_address.text = moveout.address
                ll_out_more.visibility = View.VISIBLE
            }else {
                movein.address =  data?.getStringExtra("address_name") ?: ""
                movein.uid = data?.getStringExtra("address_uid") ?: ""
                tv_in_address.text = movein.address
                ll_in_more.visibility = View.VISIBLE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    fun refreshData() {
        //fix bug
        if(moveout.address == null){
            moveout = Moveout.empty()
        }else {
            ll_out_more.visibility = View.VISIBLE
        }
        if(movein.address == null){
            movein = Movein.empty()
        }else {
            ll_in_more.visibility = View.VISIBLE
        }

        //fix bug
        if(movein.floor == 0){
            movein.floor = 1
        }
        if(moveout.floor == 0){
            moveout.floor = 1
        }

        //out
        tv_out_address.text = moveout.address
        if (moveout.is_elevator == 1){
            ll_out_floor.visibility = View.GONE
            tv_out_elevator.text = "有"
        }else {
            tv_out_elevator.text = "无"
            ll_out_floor.visibility = View.VISIBLE
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
            ll_in_floor.visibility = View.GONE
            tv_in_elevator.text = "有"
        }else {
            tv_in_elevator.text = "无"
            ll_in_floor.visibility = View.VISIBLE
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
                OptionsPickerView.OnOptionsSelectListener { option1: Int, option2: Int, option3: Int, v: View? ->
                    if (option1 == 0) {
                        moveout.is_elevator = 1
                        ll_out_floor.visibility = View.GONE
                        tv_out_elevator.text = "有"
                    } else {
                        moveout.is_elevator = 0
                        tv_out_elevator.text = "无"
                        ll_out_floor.visibility = View.VISIBLE
                    }
                }).build() as OptionsPickerView<String>
        outElevatorPicker.setPicker(elevatorOptions.toMutableList())

        // out floor
        outFloorPicker = OptionsPickerView.Builder(activity,
                OptionsPickerView.OnOptionsSelectListener { option1: Int, option2: Int, option3: Int, v: View? ->
                    moveout.floor = option1 + 1
                    tv_out_floor.text = floorsOptions[moveout.floor - 1]

                }).build() as OptionsPickerView<String>
        outFloorPicker.setPicker(floorsOptions.toMutableList())

        // out assemble
        outAssemblePicker = OptionsPickerView.Builder(activity,
                OptionsPickerView.OnOptionsSelectListener { option1: Int, option2: Int, option3: Int, v: View? ->
                    if (option1 == 0) {
                        moveout.is_handling = 1
                        tv_out_assemble.text = "需要"
                    } else {
                        moveout.is_handling = 0
                        tv_out_assemble.text = "不需要"
                    }
                }).build() as OptionsPickerView<String>
        outAssemblePicker.setPicker(assembleOptions.toMutableList())

        // in elevator
        inElevatorPicker = OptionsPickerView.Builder(activity,
                OptionsPickerView.OnOptionsSelectListener { option1: Int, option2: Int, option3: Int, v: View? ->
                    if (option1 == 0) {
                        movein.is_elevator = 1
                        ll_in_floor.visibility = View.GONE
                        tv_in_elevator.text = "有"
                    } else {
                        movein.is_elevator = 0
                        ll_in_floor.visibility = View.VISIBLE
                        tv_in_elevator.text = "无"
                    }
                }).build() as OptionsPickerView<String>
        inElevatorPicker.setPicker(elevatorOptions.toMutableList())

        // in floor
        inFloorPicker = OptionsPickerView.Builder(activity,
                OptionsPickerView.OnOptionsSelectListener { option1: Int, option2: Int, option3: Int, v: View? ->
                    movein.floor = option1 + 1
                    tv_in_floor.text = floorsOptions[movein.floor - 1]
                }).build() as OptionsPickerView<String>
        inFloorPicker.setPicker(floorsOptions.toMutableList())

        // in assemble
        inAssemblePicker = OptionsPickerView.Builder(activity,
                OptionsPickerView.OnOptionsSelectListener { option1: Int, option2: Int, option3: Int, v: View? ->
                    if (option1 == 0) {
                        movein.is_handling = 1
                        tv_in_assemble.text = "需要"
                    } else {
                        movein.is_handling = 0
                        tv_in_assemble.text = "不需要"
                    }
                }).build() as OptionsPickerView<String>
        inAssemblePicker.setPicker(assembleOptions.toMutableList())
    }
}
