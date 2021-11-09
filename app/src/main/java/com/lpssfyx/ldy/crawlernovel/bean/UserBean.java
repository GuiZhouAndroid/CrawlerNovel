package com.lpssfyx.ldy.crawlernovel.bean;

import lombok.Data;

/**
 * created by on 2021/11/8
 * 描述：管理员实体
 *
 * @author ZSAndroid
 * @create 2021-11-08-22:48
 */
@Data
public class UserBean {

    private Data data;
    private int code;
    private String msg;

    @lombok.Data
    public static class Data {
        private int id;
        private String loginName;
        private String password;
    }
}
