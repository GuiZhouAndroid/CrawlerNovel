package com.lpssfyx.ldy.crawlernovel.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lpssfyx.ldy.crawlernovel.R;
import com.lpssfyx.ldy.crawlernovel.utils.StringDialogCallback;
import com.lpssfyx.ldy.crawlernovel.utils.UserBean;
import com.lpssfyx.ldy.crawlernovel.utils.StatusBarUtils;
import com.lpssfyx.ldy.crawlernovel.utils.GsonUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class LoginActivity extends AppCompatActivity implements TextWatcher {

    private static final String TAG = "LoginActivity";

    private EditText username;
    private EditText password;
    private Button loginBtn;
    private ImageView leftArm;
    private ImageView rightArm;
    private ImageView leftHand;
    private ImageView rightHand;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtils.fullScreen(this);
        initViews();
    }

    public void initViews() {
        username = findViewById(R.id.et_user);
        password = findViewById(R.id.et_password);
        loginBtn = findViewById(R.id.bt_login);
        leftArm = findViewById(R.id.iv_left_arm);
        rightArm = findViewById(R.id.iv_right_arm);
        leftHand = findViewById(R.id.iv_left_hand);
        rightHand = findViewById(R.id.iv_right_hand);

        //监听内容改变 -> 控制按钮的点击状态
        username.addTextChangedListener(this);
        password.addTextChangedListener(this);

        //监听EidtText的焦点变化 -> 控制是否需要捂住眼睛
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    //捂住眼睛
                    close();
                } else {
                    //打开
                    open();
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });

    }

    /**
     * 管理员登录
     */
    private void doLogin() {
        OkGo.<String>post("http://112.74.52.8:8085/user/superAdminUserLogin" + "/" + username.getText().toString().trim() + "/" + password.getText().toString().trim())
                .tag("管理员登录")
                .execute(new StringDialogCallback(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UserBean userBean = GsonUtil.gsonToBean(response.body(), UserBean.class);
                        Log.i(TAG, "userBean: " + userBean);
                        if (200 == userBean.getCode() && userBean.getData() != null && userBean.getMsg().equals("success")) {
                            startActivity(new Intent(LoginActivity.this, AdminSuccessActivity.class));
                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                        if (200 == userBean.getCode() && userBean.getData() == null && userBean.getMsg().equals("error")) {
                            Toast.makeText(LoginActivity.this, "用户名和密码不匹配！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Toast.makeText(LoginActivity.this, "请求失败！", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 当有控件获得焦点focus 自动弹出键盘
     * 1. 点击软键盘的enter键 自动收回键盘
     * 2. 代码控制 InputMethodManager
     * requestFocus
     * showSoftInput:显示键盘 必须先让这个view成为焦点requestFocus
     * <p>
     * hideSoftInputFromWindow 隐藏键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //隐藏键盘
            //1.获取系统输入的管理器
            InputMethodManager inputManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

            //2.隐藏键盘
            inputManager.hideSoftInputFromWindow(username.getWindowToken(), 0);

            //3.取消焦点
            View focusView = getCurrentFocus();
            if (focusView != null) {
                focusView.clearFocus(); //取消焦点
            }

            //getCurrentFocus().clearFocus();

            //focusView.requestFocus();//请求焦点
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //判断两个输入框是否有内容
        if (username.getText().toString().length() > 0 &&
                password.getText().toString().length() > 0) {
            //按钮可以点击
            loginBtn.setEnabled(true);
        } else {
            //按钮不能点击
            loginBtn.setEnabled(false);
        }
    }

    public void close() {
        //左边
        RotateAnimation lAnim = new RotateAnimation(0, 170, leftArm.getWidth(), 0f);
        lAnim.setDuration(500);
        lAnim.setFillAfter(true);

        leftArm.startAnimation(lAnim);

        RotateAnimation rAnim = new RotateAnimation(0, -170, 0f, 0f);
        rAnim.setDuration(500);
        rAnim.setFillAfter(true);

        rightArm.startAnimation(rAnim);

        TranslateAnimation down = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.hand_down_anim);
        leftHand.startAnimation(down);
        rightHand.startAnimation(down);
    }

    public void open() {
        //左边
        RotateAnimation lAnim = new RotateAnimation(170, 0, leftArm.getWidth(), 0f);
        lAnim.setDuration(500);
        lAnim.setFillAfter(true);

        leftArm.startAnimation(lAnim);

        RotateAnimation rAnim = new RotateAnimation(-170, 0, 0f, 0f);
        rAnim.setDuration(500);
        rAnim.setFillAfter(true);

        rightArm.startAnimation(rAnim);

        TranslateAnimation up = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.hand_up_anim);
        leftHand.startAnimation(up);
        rightHand.startAnimation(up);
    }
}