package com.atguigu.headline.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * ClassName: NewsHeadline
 * Package: com.atguigu.headline.pojo
 * Description:新闻实体类，对应数据库news_headline
 *
 * @Author Aran
 * @Create 2024/6/10 16:19
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsHeadline implements Serializable {
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
    private Integer publisher;
    private Integer pageViews;
    private Date createTime;
    private Date updateTime;
    private Integer isDeleted;

}
