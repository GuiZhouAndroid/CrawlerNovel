package com.lpssfyx.ldy.crawlernovel.bean;

import lombok.Data;

/**
 * created by on 2021/11/9
 * 描述：定制小说信息返回界面内容  小说编号、小说名称、小说作者、爬取时间
 *
 * @author 龙大艳
 * @create 2021-11-09-13:26
 */

@Data
public class BookInfoBean {
    private Integer id;
    private String bookName;
    private String author;
    private String updateTime;

    public BookInfoBean() {
    }

    public BookInfoBean(Integer id, String bookName, String author, String updateTime) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.updateTime = updateTime;
    }
}
