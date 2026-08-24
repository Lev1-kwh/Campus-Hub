package com.campushub.controller;

import com.campushub.dto.PostRequest;
import com.campushub.dto.PostResponse;
import com.campushub.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
@PostMapping
    public PostResponse sendPost(@RequestBody PostRequest postRequest, HttpServletRequest request){
        Long userId = (Long)request.getAttribute("userId");
        postService.post(postRequest,userId);
        PostResponse postResponse = new PostResponse();
        postResponse.setMessage("帖子发布成功");
        return postResponse;
    }
}
