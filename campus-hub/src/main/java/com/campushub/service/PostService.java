package com.campushub.service;

import com.campushub.dto.PostRequest;
import com.campushub.entity.Post;

import java.util.List;

public interface PostService {
    public void post(PostRequest postRequest,Long userId);
    public List<Post> getPostList();
}
