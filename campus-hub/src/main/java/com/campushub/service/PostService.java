package com.campushub.service;

import com.campushub.dto.PostRequest;

public interface PostService {
    public void post(PostRequest postRequest,Long userId);

}
