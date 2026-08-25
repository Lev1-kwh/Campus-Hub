package com.campushub.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("post_like")
public class PostLike {
@TableId
    private Long id;
    private Long userId;
    private Long postId;
    private LocalDateTime createTime;
}
