package com.campushub.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campushub.dto.PostRequest;
import com.campushub.entity.Post;
import com.campushub.entity.PostLike;
import com.campushub.mapper.PostLikeMapper;
import com.campushub.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private final PostMapper postMapper;
    private final PostLikeMapper postLikeMapper;

    public PostServiceImpl(PostMapper postMapper,PostLikeMapper postLikeMapper) {
        this.postMapper = postMapper;
        this.postLikeMapper = postLikeMapper;

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
    @Override
    public Post getPostById(Long id){
        //根据帖子id查询帖子
        Post post = postMapper.selectById(id);
        //查询后浏览量加1
        post.setViewCount(post.getViewCount()+1);
        //更新帖子
        postMapper.updateById(post);
        return post;
    }
    @Override
   public PostLike selectPostLike(Long userId,Long postId){
       QueryWrapper<PostLike> wrapper = new QueryWrapper<>();
       wrapper.eq("user_id", userId)
               .eq("post_id", postId);

       PostLike postLike = postLikeMapper.selectOne(wrapper);
       return postLike;
   }

   @Override
   public void likePost (Long userId,Long postId){
        //查询用户是否点赞过该帖子
        PostLike postLike = selectPostLike(userId,postId);
        if (postLike==null){
        //如果点赞记录为空则创建点赞记录
            PostLike like = new PostLike();
            like.setPostId(postId);
            like.setUserId(userId);
            like.setCreateTime(LocalDateTime.now());
        //在点赞表中新插入该点赞记录
            postLikeMapper.insert(like);
        //该帖子点赞数+1
            Post post = postMapper.selectById(postId);
            post.setLikeCount(post.getLikeCount()+1);
        //更新帖子状态
            postMapper.updateById(post);

   }
    }
}
