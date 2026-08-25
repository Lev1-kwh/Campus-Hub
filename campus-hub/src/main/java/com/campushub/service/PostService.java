package com.campushub.service;

import com.campushub.dto.PostRequest;
import com.campushub.entity.Post;
import com.campushub.entity.PostLike;

import java.util.List;

public interface PostService {
    public void post(PostRequest postRequest,Long userId);
    public List<Post> getPostList();
    public Post getPostById(Long id);
    public PostLike selectPostLike(Long userId,Long postId);
    public void likePost (Long userId,Long postId);
}
