package com.campushub.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campushub.dto.CommentRequest;
import com.campushub.dto.PostRequest;
import com.campushub.entity.Comment;
import com.campushub.entity.Post;
import com.campushub.entity.PostLike;
import com.campushub.mapper.CommentMapper;
import com.campushub.mapper.PostLikeMapper;
import com.campushub.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private final PostMapper postMapper;
    private final PostLikeMapper postLikeMapper;
    private final CommentMapper commentMapper;

    public PostServiceImpl(PostMapper postMapper
            ,PostLikeMapper postLikeMapper
            ,CommentMapper commentMapper) {
        this.postMapper = postMapper;
        this.postLikeMapper = postLikeMapper;
        this.commentMapper = commentMapper;

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
        post.setCommentCount(0);
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
   //涉及多个数据库操作，添加事务注解将数据库操作绑定在一起
   @Transactional
   public boolean likePost (Long userId,Long postId){
        //查询用户是否点赞过该帖子
        PostLike postLike = selectPostLike(userId,postId);
       Post post = postMapper.selectById(postId);
        if (postLike==null) {
            //如果点赞记录为空则创建点赞记录
            PostLike like = new PostLike();
            like.setPostId(postId);
            like.setUserId(userId);
            like.setCreateTime(LocalDateTime.now());
            //在点赞表中新插入该点赞记录
            postLikeMapper.insert(like);
            //该帖子点赞数+1
            post.setLikeCount(post.getLikeCount() + 1);
            //更新帖子状态
            postMapper.updateById(post);
            return true;
        }
       else{
            postLikeMapper.deleteById(postLike.getId());
            if (post.getLikeCount()>0){
            post.setLikeCount(post.getLikeCount()-1);
            }
            postMapper.updateById(post);
            return false;
   }
    }
    @Override
    @Transactional
    public void commentPost(Long userId, Long postId, CommentRequest request){
        //评论业务逻辑基本与点赞一致
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setCreateTime(LocalDateTime.now());
        Post post = postMapper.selectById(postId);
        post.setCommentCount(post.getCommentCount()+1);
        commentMapper.insert(comment);
        postMapper.updateById(post);
    }
    @Override
    @Transactional
    public void deleteComment(Long userId,Long commentId){
        // 查询评论
        Comment comment = commentMapper.selectById(commentId);
        if (comment == null) {
            throw new RuntimeException("该评论不存在");
        }
        // 查询评论所属帖子
        Post post = postMapper.selectById(comment.getPostId());
        if (post == null) {
            throw new RuntimeException("该帖子不存在");
        }
        // 权限校验：只有评论作者可以删除
        if (!comment.getUserId().equals(userId)) {
            throw new RuntimeException("无权限删除该评论");
        }
        // 删除评论
        commentMapper.deleteById(commentId);
        // 评论数 -1
        if (post.getCommentCount() > 0) {
            post.setCommentCount(post.getCommentCount() - 1);
        }
        // 更新帖子
        postMapper.updateById(post);
    }
    @Override
    public List<Comment> selectPostComment(Long postId){
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postId);
        List<Comment> comments =commentMapper.selectList(wrapper);
        return comments;
    }
}
