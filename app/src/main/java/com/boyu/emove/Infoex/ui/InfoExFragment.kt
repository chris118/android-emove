package com.boyu.emove.Infoex.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TimePicker
import androidx.lifecycle.Observer
import com.bigkoo.pickerview.OptionsPickerView
import com.bigkoo.pickerview.TimePickerView
import com.boyu.emove.Infoex.entity.*
import com.boyu.emove.Infoex.viewmodel.InfoexViewModel
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.extension.toEditable
import com.boyu.emove.utils.KeyboardktUtils
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_info_ex.*
import java.util.*
import kotlin.collections.ArrayList

class InfoExFragment : BaseNaviFragment() {

    private val TAG = InfoExFragment::class.java.simpleName
    private var viewModel: InfoexViewModel? = null

    private var timePicker: TimePickerView? = null
    private var timeSlot: List<TimeSlot>? = null
    private val durationOptions: MutableList<String> = ArrayList()
    private var durationPickerView: OptionsPickerView<String>? = null
    private val invoiceOptions = arrayOf("需要", "不需要")
    private var invoicePicker: OptionsPickerView<String>? = null


    private var infoEx: InfoEx? = null


    override fun onNext() {
        infoEx?.run {
            cart_contacts.user_name = tv_out_name.text.toString()
            cart_contacts.user_telephone = tv_out_mobile.text.toString()
            cart_contacts.user_note = tv_out_note.text.toString()

            cart_time.year

            var infoexBody = InfoExBody(
                    cart_time.year.toInt(),
                    cart_time.month.toInt(),
                    cart_time.day.toInt(),
                    cart_time.time_slot_id.toInt(),
                    cart_contacts.user_name,
                    cart_contacts.user_telephone,
                    cart_contacts.is_invoice,
                    cart_contacts.user_note
            )

            viewModel?.updateInfoex(infoexBody)
        }
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_infoExFragment_to_vehicleFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {
            this.getInfoexResponse.observe(this@InfoExFragment, Observer {
                if(it.code == 0){
                    durationOptions.clear()
                    for (d in it.result.time_slot) {
                        durationOptions.add(d.title)
                    }
                    durationPickerView?.setPicker(durationOptions.toMutableList())
                    infoEx = it.result
                    if(infoEx?.cart_contacts == null){
                        infoEx?.cart_contacts =  CartContacts("","","", 0)
                    }
                    if(infoEx?.cart_time == null){
                        val calendar = Calendar.getInstance()

                        calendar.time = Date()
                        val year = calendar.get(Calendar.YEAR)
                        val month = calendar.get(Calendar.MONTH)
                        val day = calendar.get(Calendar.DAY_OF_MONTH)

                        val default_slot_id = infoEx?.time_slot!![0].time_slot_id.toString()
                        infoEx?.cart_time = CartTime(year.toString(), month.toString(), day.toString(), default_slot_id)
                    }
                    updateUI()
                }
            })

            this.updateInfoExResponse.observe(this@InfoExFragment, Observer {
                if(it.code == 0){
                    goNext()
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_info_ex, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeView()
        loadData()
    }

    private fun initializeView(){
        ll_c_infoex.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
        }
        ll_out_time.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            timePicker?.show()
        }

        ll_out_duratioon.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            durationPickerView?.show()
        }

        //time picker
        timePicker = TimePickerView(TimePickerView.Builder(activity, object: TimePicker.OnTimeChangedListener, TimePickerView.OnTimeSelectListener {
            override fun onTimeChanged(p0: TimePicker?, p1: Int, p2: Int) {
            }

            override fun onTimeSelect(date: Date?, v: View?) {
                infoEx?.run {
                    val calendar = Calendar.getInstance()

                    calendar.time = date
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH)
                    val day = calendar.get(Calendar.DAY_OF_MONTH)

                    cart_time.year = year.toString()
                    cart_time.month = month.toString()
                    cart_time.day = day.toString()

                    tv_out_time.text = cart_time.year + "-" + cart_time.month + "-" + cart_time.day
                }

            }
        })
         .setType(booleanArrayOf(true, true, true, false, false, false)))

        ll_out_invoice.setOnClickListener {
            KeyboardktUtils.hideKeyboard(it)
            invoicePicker?.show()
        }

        // duration picker
        durationPickerView = OptionsPickerView.Builder(activity,
                OptionsPickerView.OnOptionsSelectListener { option1: Int, option2: Int, option3: Int, v: View? ->
                    infoEx?.run {
                        cart_time.time_slot_id = time_slot[option1].time_slot_id.toString()
                        tv_out_duration.text = time_slot[option1].title
                    }

                }).build() as OptionsPickerView<String>

        // invoice picker
        invoicePicker = OptionsPickerView.Builder(activity,
                OptionsPickerView.OnOptionsSelectListener { option1: Int, option2: Int, option3: Int, v: View? ->
                    if (option1 == 0) {
                        tv_out_invoice.text = "需要"
                        infoEx?.run {
                            cart_contacts.is_invoice = 1
                        }
                    } else {
                        tv_out_invoice.text = "不需要"
                        infoEx?.run {
                            cart_contacts.is_invoice = 0
                        }
                    }
                }).build() as OptionsPickerView<String>
        invoicePicker?.setPicker(invoiceOptions.toMutableList())
    }

    private fun loadData(){
        viewModel?.getInfoex()
    }

    private fun updateUI() {
        infoEx?.run {
            tv_out_time.text = cart_time.year + "-" + cart_time.month + "-" + cart_time.day
            var duration = ""
            for(ts in time_slot) {
                if (cart_time.time_slot_id.toInt() == ts.time_slot_id) {
                    duration = ts.title
                    break
                }
            }
            tv_out_duration.text = duration

            tv_out_name.text = cart_contacts.user_name.toEditable()
            tv_out_mobile.text = cart_contacts.user_telephone.toEditable()
            tv_out_note.text = cart_contacts.user_note.toEditable()
            tv_out_invoice.text = if(cart_contacts.is_invoice == 1) "需要" else "不需要"
        }
    }
}
