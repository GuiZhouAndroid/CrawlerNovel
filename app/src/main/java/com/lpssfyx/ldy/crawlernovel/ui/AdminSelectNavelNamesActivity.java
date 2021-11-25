package com.lpssfyx.ldy.crawlernovel.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpssfyx.ldy.crawlernovel.R;
import com.lpssfyx.ldy.crawlernovel.adapter.BaseNovelInfoAdapter;
import com.lpssfyx.ldy.crawlernovel.bean.BookBean;
import com.lpssfyx.ldy.crawlernovel.bean.BookInfoBean;
import com.lpssfyx.ldy.crawlernovel.utils.GsonUtil;
import com.lpssfyx.ldy.crawlernovel.utils.StringDialogCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * created by on 2021/11/9
 * 描述：查询小说信息
 *
 * @author 龙大艳
 * @create 2021-11-09-12:43
 */
public class AdminSelectNavelNamesActivity extends AppCompatActivity {
    private static final String TAG = "AdminSelectNavelNamesActivity";
    private RecyclerView recyclerViewNavel;
    private BaseNovelInfoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_navel_names);
        initView();
        initRecyclerView();
        selectAllNovelNames();
    }

    private void initView() {
        recyclerViewNavel = findViewById(R.id.recyclerView_novel);
    }

    private void initRecyclerView() {
        // 1.创建布局管理实例对象
        LinearLayoutManager layoutManager = new LinearLayoutManager(AdminSelectNavelNamesActivity.this);
        // 2.设置RecyclerView布局方式为垂直方向
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 3.RecyclerView绑定携带垂直方向参数的布局管理实例对象
        recyclerViewNavel.setLayoutManager(layoutManager);
    }

    /**
     * 开始查询小说信息
     */
    private void selectAllNovelNames() {
        OkGo.<String>post("http://112.74.52.8:8085/book/selectBookAllInfo")
                .tag("获取全部用户信息")
                .execute(new StringDialogCallback(this) {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onSuccess(Response<String> response) {
                        BookBean bookBean = GsonUtil.gsonToBean(response.body(), BookBean.class);
                        Log.i("bookBean", "bookBean: === " + bookBean);
                        if (200 == bookBean.getCode() && bookBean.getData() != null && bookBean.getMsg().equals("success")) {
                            List<BookBean.Data> list = bookBean.getData();
                            List<BookInfoBean> bookInfoBeanList = new ArrayList<>();

                            Log.i(TAG, "list: " + list);
                            for (BookBean.Data bookInfo : list) {
                                BookInfoBean bookInfoBean = new BookInfoBean(bookInfo.getId(), bookInfo.getBookName(), bookInfo.getAuthor(), bookInfo.getUpdateTime());
                                bookInfoBeanList.add(bookInfoBean);
                            }

                            adapter = new BaseNovelInfoAdapter(R.layout.admin_recycler_view_novel_item, bookInfoBeanList);
                            recyclerViewNavel.setAdapter(adapter);
//                            //创建适配器

                            return;
                        }
                        if (200 == bookBean.getCode() && bookBean.getData() == null && bookBean.getMsg().equals("error")) {
                            Toast.makeText(AdminSelectNavelNamesActivity.this, "查询失败！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Toast.makeText(AdminSelectNavelNamesActivity.this, "查询失败！", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
