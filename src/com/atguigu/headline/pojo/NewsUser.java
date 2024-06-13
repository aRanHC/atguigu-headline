package com.atguigu.headline.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: NewsUser
 * Package: com.atguigu.headline.pojo
 * Description: 新闻发布者实体类，对应数据库news_user表。
 *
 * @Author Aran
 * @Create 2024/6/10 16:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsUser implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;
    private String nickName;
}
