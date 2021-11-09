package com.lpssfyx.ldy.crawlernovel.adapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lpssfyx.ldy.crawlernovel.R;
import com.lpssfyx.ldy.crawlernovel.bean.BookBean;
import com.lpssfyx.ldy.crawlernovel.bean.BookInfoBean;
import com.lpssfyx.ldy.crawlernovel.bean.UserInfoBean;

import java.util.List;

/**
 * created by on 2021/11/9
 * 描述：列表适配器，自动填充展示全部用户信息
 *
 * @author 龙大艳
 * @create 2021-11-09-11:59
 */
public class BaseNovelInfoAdapter extends BaseQuickAdapter<BookInfoBean, BaseViewHolder> {

    public BaseNovelInfoAdapter(@LayoutRes int layoutResId, @Nullable List<BookInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, BookInfoBean bookInfoBean) {
        //可链式调用赋值
        baseViewHolder.setText(R.id.novel_id, bookInfoBean.getId())
                .setText(R.id.novel_name,bookInfoBean.getBookName())
                .setText(R.id.novel_author,bookInfoBean.getAuthor())
                .setText(R.id.novel_time,bookInfoBean.getUpdateTime());
    }
}
