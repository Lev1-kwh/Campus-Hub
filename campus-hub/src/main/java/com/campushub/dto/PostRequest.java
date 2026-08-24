package com.campushub.dto;

import lombok.Data;

@Data
public class PostRequest {
    private String title;
    private String content;
    private String category;
    private String imageUrl;
}
