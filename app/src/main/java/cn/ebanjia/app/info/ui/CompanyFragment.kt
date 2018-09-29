package cn.ebanjia.app.info.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.ebanjia.app.R
import kotlinx.android.synthetic.main.fragment_company.*

class CompanyFragment : Fragment() {
    internal var saveCompanyOrder: (name: String, contact: String, mobile: String) -> Unit = {_,_,_ -> }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        btn_company_book.setOnClickListener {
            saveCompanyOrder(tv_company_name.text.toString(),
                    tv_company_contact.text.toString(),
                    tv_company_mobile.text.toString())
        }
    }
}
