package com.campushub.service;

import com.campushub.dto.CommentRequest;
import com.campushub.dto.PostRequest;
import com.campushub.entity.Comment;
import com.campushub.entity.Post;
import com.campushub.entity.PostLike;

import java.util.List;

public interface PostService {
    void post(PostRequest postRequest, Long userId);
    List<Post> getPostList();
    Post getPostById(Long id);
    PostLike selectPostLike(Long userId, Long postId);
    boolean likePost(Long userId, Long postId);
    void commentPost(Long userId, Long postId, CommentRequest request);
    List<Comment> selectPostComments(Long postId);
    void deleteComment(Long userId, Long commentId);
    void deletePost(Long userId, Long postId);
}
