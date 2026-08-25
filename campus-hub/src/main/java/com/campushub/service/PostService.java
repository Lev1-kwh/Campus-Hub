package com.campushub.service;

import com.campushub.dto.CommentRequest;
import com.campushub.dto.PostRequest;
import com.campushub.entity.Comment;
import com.campushub.entity.Post;
import com.campushub.entity.PostLike;

import java.util.List;

public interface PostService {
    public void post(PostRequest postRequest,Long userId);
    public List<Post> getPostList();
    public Post getPostById(Long id);
    public PostLike selectPostLike(Long userId,Long postId);
    public boolean likePost (Long userId,Long postId);
    public void commentPost(Long userId, Long postId, CommentRequest request);
    public List<Comment> selectPostComment(Long postId);
    public void deleteComment(Long userId,Long commentId);
}
