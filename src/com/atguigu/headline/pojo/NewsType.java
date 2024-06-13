package com.atguigu.headline.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: NewsType
 * Package: com.atguigu.headline.pojo
 * Description: 新闻类型实体类，对应数据库news_type。
 *
 * @Author Aran
 * @Create 2024/6/10 16:16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsType implements Serializable {
    private Integer tid;
    private String tname;
}
