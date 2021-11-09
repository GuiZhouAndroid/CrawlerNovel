package com.lpssfyx.ldy.crawlernovel.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * created by on 2021/11/8
 * 描述：SpringBoot返回的全部用户Json数据
 *
 * @author 龙大艳
 * @create 2021-11-08-23:21
 */
@Data
public class SelectAllUserInfo implements Serializable {

    private List<Data> data;
    private int code;
    private String msg;

    @lombok.Data
    public static class Data {
        private int id;
        private String loginName;
        private Object password;

        public Data(int id, String loginName) {
            this.id = id;
            this.loginName = loginName;
        }
    }
}
