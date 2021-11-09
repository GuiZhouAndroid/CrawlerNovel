package com.lpssfyx.ldy.crawlernovel.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpssfyx.ldy.crawlernovel.R;
import com.lpssfyx.ldy.crawlernovel.bean.SelectAllUserInfo;
import com.lpssfyx.ldy.crawlernovel.bean.UserInfoBean;
import com.lpssfyx.ldy.crawlernovel.utils.GsonUtil;
import com.lpssfyx.ldy.crawlernovel.adapter.BaseUserInfoAdapter;
import com.lpssfyx.ldy.crawlernovel.utils.StatusBarUtils;
import com.lpssfyx.ldy.crawlernovel.utils.StringDialogCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * created by on 2021/11/8
 * 描述：登录成功后，自动查询全部用户信息
 *
 * @author 龙大艳
 * @create 2021-11-08-22:48
 */
public class AdminSuccessActivity extends AppCompatActivity {
    private static final String TAG = "AdminSuccessActivity";
    private RecyclerView recyclerViewUser;
    private BaseUserInfoAdapter adapter;
    Button start_select;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_success);
        StatusBarUtils.fullScreen(this);
        initView();
        initRecyclerView();
        selectAllUserInfo();
    }

    private void initView() {
        recyclerViewUser = findViewById(R.id.recyclerView_user);
        start_select = findViewById(R.id.start_select);
        start_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminSuccessActivity.this,AdminSelectNavelNamesActivity.class));
            }
        });
        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
    }

    private void initRecyclerView() {
        //创建布局管理, Recycle布局方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewUser.setLayoutManager(layoutManager);
    }

    private void selectAllUserInfo() {
        OkGo.<String>post("http://112.74.52.8:8085/user/selectUserAllInfo")
                .tag("获取全部用户信息")
                .execute(new StringDialogCallback(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        SelectAllUserInfo selectAllUserInfo = GsonUtil.gsonToBean(response.body(), SelectAllUserInfo.class);
                        if (200 == selectAllUserInfo.getCode() && selectAllUserInfo.getData() != null && selectAllUserInfo.getMsg().equals("success")) {
                            UserInfoBean userInfoBean = null;
                            List<SelectAllUserInfo.Data> list = selectAllUserInfo.getData();
                            List<UserInfoBean> userInfoBeanList = new ArrayList<>();
                            for (SelectAllUserInfo.Data userInfo : list) {
                                userInfoBean = new UserInfoBean(userInfo.getId(), userInfo.getLoginName());
                                userInfoBeanList.add(userInfoBean);
                            }

                            //创建适配器
                            adapter = new BaseUserInfoAdapter(R.layout.admin_recycler_view_user_item, userInfoBeanList);
                            recyclerViewUser.setAdapter(adapter);
                            return;
                        }
                        if (200 == selectAllUserInfo.getCode() && selectAllUserInfo.getData() == null && selectAllUserInfo.getMsg().equals("error")) {
                            Toast.makeText(AdminSuccessActivity.this, "查询失败！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Toast.makeText(AdminSuccessActivity.this, "查询失败！", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
