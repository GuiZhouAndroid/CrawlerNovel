package com.lpssfyx.ldy.crawlernovel.bean;

import lombok.Data;

/**
 * created by on 2021/11/9
 * 描述：查询界面定制返回内容，只包含用户编号、和用户姓名
 *
 * @author 龙大艳
 * @create 2021-11-09-11:37
 */
@Data
public class UserInfoBean {
    private int id;
    private String loginName;

    public UserInfoBean() {
    }

    public UserInfoBean(int id, String loginName) {
        this.id = id;
        this.loginName = loginName;
    }
}
