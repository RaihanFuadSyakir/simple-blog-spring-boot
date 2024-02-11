package com.blog.simpleblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.simpleblog.repository.CommentJpaRepositry;
import com.blog.simpleblog.vo.Comment;

@Service
public class CommentService {
    @Autowired
    CommentJpaRepositry commentJpaRepositry;

    public boolean saveComment(Comment comment) {
        Comment result = commentJpaRepositry.save(comment);
        boolean isSuccess = true;
        if (result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public List<Comment> getCommentList(Long postId) {
        List<Comment> comments = commentJpaRepositry.findAllByPostIdOrderByRegDateDesc(postId);
        return comments;
    }

    public Comment getComment(Long id) {
        Comment comment = commentJpaRepositry.findOneById(id);
        return comment;
    }

    public List<Comment> findCommentByPostIdAndCommentLikeDesc(Long id, String query) {
        List<Comment> comments = commentJpaRepositry.findByPostIdAndCommentContainingOrderByRegDateDesc(id, query);
        return comments;
    }

    public boolean deleteComment(Long id) {
        Comment comment = commentJpaRepositry.findOneById(id);
        boolean isSuccess = true;
        if (comment == null) {
            return false;
        }
        commentJpaRepositry.delete(comment);
        return isSuccess;
    }
}
