package cn.ebanjia.app.kanjia


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.ebanjia.app.R
import cn.ebanjia.app.base.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_kanjiaragment.*

class KanjiaFragment : BaseFragment() {
    private var order_id = 0

    override fun getTargetLayoutId(): Int {
        return 0
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kanjiaragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val safeArgs = KanjiaFragmentArgs.fromBundle(it)
            order_id = safeArgs.orderId
        }

        webview.loadUrl("https://www.ebanjia.cn/cut/" + order_id)
    }
}
