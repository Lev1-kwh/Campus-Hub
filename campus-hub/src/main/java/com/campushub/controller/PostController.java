package com.campushub.controller;

import com.campushub.dto.CommentRequest;
import com.campushub.dto.CommentResponse;
import com.campushub.dto.PostRequest;
import com.campushub.dto.PostResponse;
import com.campushub.entity.Comment;
import com.campushub.entity.Post;
import com.campushub.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //发布帖子
    @PostMapping
    public PostResponse sendPost(@RequestBody PostRequest postRequest, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        postService.post(postRequest, userId);
        PostResponse postResponse = new PostResponse();
        postResponse.setMessage("帖子发布成功");
        return postResponse;
    }

    //查询所有帖子
    @GetMapping("/list")
    public List<Post> getPostList() {
        return postService.getPostList();
    }

    //根据帖子id查询帖子
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    //点赞帖子
    @PostMapping("/{id}/like")
    public PostResponse postLike(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        boolean liked = postService.likePost(userId, id);
        PostResponse response = new PostResponse();
        if (liked) {
            response.setMessage("点赞成功");
        } else {
            response.setMessage("取消点赞成功");
        }
        return response;
    }

    @PostMapping("/{id}/comment")
    public CommentResponse postComment(@PathVariable Long id, @RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        postService.commentPost(userId, id, commentRequest);
        CommentResponse response = new CommentResponse();
        response.setMessage("评论成功");
        return response;
    }

    @GetMapping("/{id}/commentlist")
    public List<Comment> selectCommentsById(@PathVariable Long id) {
        return postService.selectPostComment(id);
    }

    @DeleteMapping("/comment/{commentId}")
    public CommentResponse deleteComment(@PathVariable Long commentId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        postService.deleteComment(userId, commentId);
        CommentResponse response = new CommentResponse();
        response.setMessage("评论删除成功");
        return response;
    }
}