package com.blog.simpleblog.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.blog.simpleblog.vo.Comment;

public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("id"));
        comment.setPostId(rs.getLong("post_id"));
        comment.setUser(rs.getString("user"));
        comment.setComment(rs.getString("comment"));
        comment.setRegDate(rs.getDate("reg_date"));
        return comment;
    }
}
