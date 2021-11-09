package com.lpssfyx.ldy.crawlernovel.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.lpssfyx.ldy.crawlernovel.R;
import com.lpssfyx.ldy.crawlernovel.bean.SelectAllUserInfo;
import com.lpssfyx.ldy.crawlernovel.utils.StringDialogCallback;
import com.lpssfyx.ldy.crawlernovel.utils.GsonUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by on 2021/11/8
 * 描述：
 *
 * @author 龙大艳
 * @create 2021-11-08-22:48
 */
public class AdminSuccessActivity extends AppCompatActivity {
    private static final String TAG = "AdminSuccessActivity";
    RecyclerView rl_view;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_success);
        selectAllUserInfo();
    }

    private void selectAllUserInfo() {
        OkGo.<String>post("http://112.74.52.8:8085/user/selectUserAllInfo")
                .tag("获取全部用户信息")
                .execute(new StringDialogCallback(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        SelectAllUserInfo selectAllUserInfo = GsonUtil.gsonToBean(response.body(), SelectAllUserInfo.class);
                        if (200 == selectAllUserInfo.getCode() && selectAllUserInfo.getData() != null && selectAllUserInfo.getMsg().equals("success")) {
                            List<SelectAllUserInfo.Data> list1 = new ArrayList<>();
                            List<String> username = new ArrayList<>();
                            List<Integer> id = new ArrayList<>();
                            for (SelectAllUserInfo.Data data :selectAllUserInfo.getData()) {
                                SelectAllUserInfo.Data d= new SelectAllUserInfo.Data(data.getId(),data.getLoginName());
                                list1.add(d);
                                username.add(d.getLoginName());
                                id.add(d.getId());
                                AlertDialog alertDialog1 = new AlertDialog.Builder(AdminSuccessActivity.this)
                                        .setTitle("全部用户")//标题
                                        .setMessage("系统当前用户有"+Arrays.toString(username.toArray()))//内容
                                        .setIcon(R.mipmap.ic_launcher)//图标
                                        .create();
                                alertDialog1.show();
                            }

//                            //纵向展示
//                            rl_view.setLayoutManager(new LinearLayoutManager(AdminSuccessActivity.this,RecyclerView.VERTICAL,false));
//                            //存入适配器
//                            Recyadap recyadap = new Recyadap(AdminSuccessActivity.this,selectAllUserInfo.getData());
//                            rl_view.setAdapter(recyadap);
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
