package com.lpssfyx.ldy.crawlernovel.adapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lpssfyx.ldy.crawlernovel.R;
import com.lpssfyx.ldy.crawlernovel.bean.UserInfoBean;

import java.util.List;

/**
 * created by on 2021/11/9
 * 描述：列表适配器，自动填充展示全部用户信息
 *
 * @author 龙大艳
 * @create 2021-11-09-11:59
 */
public class BaseUserInfoAdapter extends BaseQuickAdapter<UserInfoBean, BaseViewHolder> {

    public BaseUserInfoAdapter(@LayoutRes int layoutResId, @Nullable List<UserInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserInfoBean item) {
        //可链式调用赋值
        helper.setText(R.id.user_id, String.valueOf(item.getId())).setText(R.id.username,item.getLoginName());
        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}
