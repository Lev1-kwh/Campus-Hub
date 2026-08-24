package com.campushub.service;

import com.campushub.dto.PostRequest;
import com.campushub.entity.Post;
import com.campushub.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private final PostMapper postMapper;

    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;

    }



    @Override
    public void post(PostRequest postRequest,Long userId){
        Post post = new Post();
        //绑定当前登录用户
        post.setUserId(userId);
        //将DTO的属性注入实体对象post并进行补充
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setCategory(postRequest.getCategory());
        post.setImageUrl(postRequest.getImageUrl());
        //初始化帖子数据
        post.setViewCount(0);
        post.setLikeCount(0);
        post.setStatus(1);
        postMapper.insert(post);
    }
    @Override
    public List<Post> getPostList(){
        return postMapper.selectList(null);

    }

}
