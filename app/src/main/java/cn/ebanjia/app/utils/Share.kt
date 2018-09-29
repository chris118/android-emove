package cn.ebanjia.app.utils

import cn.sharesdk.onekeyshare.OnekeyShare
import android.content.Context
import cn.sharesdk.wechat.friends.Wechat
import cn.sharesdk.wechat.moments.WechatMoments

/**
 * Created by chrisw on 2018/9/29.
 */
class Share {
    fun shareWeChat(context: Context, oreder_id: Int) {
        val oks = OnekeyShare()
        //关闭sso授权
        oks.disableSSOWhenAuthorize()

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("快来帮我砍价搬家")
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我在【e搬家】平台下单搬家了，请来帮我砍价吧，最低可砍到免费搬家！【e搬家】快乐搬新家!")

        //网络图片的url：所有平台
        oks.setImageUrl("http://www.ebanjia.cn/statics/front/images/kanjia/bg_top.png")

        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("https://www.ebanjia.cn/cut/$oreder_id")

//        oks.setPlatform(Wechat.NAME)
//        oks.setPlatform(WechatMoments.NAME)

        // 启动分享GUI
        oks.show(context)
    }
}