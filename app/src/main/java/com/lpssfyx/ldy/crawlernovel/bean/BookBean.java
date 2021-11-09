package com.lpssfyx.ldy.crawlernovel.bean;

import java.util.List;

import lombok.Data;

/**
 * created by on 2021/11/9
 * 描述：
 *
 * @author 龙大艳
 * @create 2021-11-09-13:07
 */

@Data
public class BookBean {

    private List<Data> data;
    private Integer code;
    private String msg;

    @lombok.Data
    public static class Data {
        private Integer id;
        private Object catId;
        private Object picUrl;
        private String bookName;
        private String author;
        private Object bookDesc;
        private Object score;
        private Object bookStatus;
        private Object visitCount;
        private String updateTime;
        private Object softCat;
        private Object softTag;
    }
}
