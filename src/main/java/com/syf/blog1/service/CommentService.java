package com.syf.blog1.service;

import com.syf.blog1.po.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);

}
