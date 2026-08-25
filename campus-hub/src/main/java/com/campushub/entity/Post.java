package com.campushub.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("post")
public class Post {
    @TableId
    private Long id;//使用Long而不是long，因为数据库主键在插入前可能为空
    private Long userId;
    private String title;
    private String content;
    private String category;
    private String imageUrl;//图片地址
    private Integer viewCount;//类型设置为Integer而不是是避免数据库数据为null时映射到java中的int时不会报错，int类型变量不能为null
    private Integer likeCount;
    private Integer commentCount;
    private Integer status;//帖子状态
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
/*
MySQL->Java
bigint->Long
int->Integer
varchar->String
datetime->LocalDateTime
 */

}
