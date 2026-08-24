package com.campushub.controller;

import com.campushub.dto.PostRequest;
import com.campushub.dto.PostResponse;
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
    public PostResponse sendPost(@RequestBody PostRequest postRequest, HttpServletRequest request){
        Long userId = (Long)request.getAttribute("userId");
        postService.post(postRequest,userId);
        PostResponse postResponse = new PostResponse();
        postResponse.setMessage("帖子发布成功");
        return postResponse;
    }
    //查询所有帖子
@GetMapping("/list")
    public List<Post> getPostList(){
        return postService.getPostList();
}
}
