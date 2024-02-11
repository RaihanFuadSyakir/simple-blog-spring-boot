package com.blog.simpleblog.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.blog.simpleblog.mapper.PostMapper;
import com.blog.simpleblog.vo.Post;

@Repository
public class PostRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Post findOne(Long id) {
        String sql = "select * from post where id = ?";
        RowMapper<Post> rowMapper = new PostMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<Post> findPost() {
        String sql = "select * from post order by updt_date desc";
        RowMapper<Post> rowMapper = new PostMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<Post> findPostOrderByUpdtDateAsc() {
        String sql = "SELECT * FROM post ORDER BY updt_date Asc";
        RowMapper<Post> rowMapper = new PostMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<Post> findPostOrderByRegDateDesc() {
        String sql = "SELECT * FROM post ORDER BY reg_date Desc";
        RowMapper<Post> rowMapper = new PostMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<Post> findPostLikeTitle(String query) {
        String sql = "select * from post where title like ?";
        RowMapper<Post> rowMapper = new PostMapper();
        return this.jdbcTemplate.query(sql, rowMapper, '%' + query + '%');
    }

    public List<Post> findPostLikeContent(String query) {
        String sql = "select * from post where content like ?";
        RowMapper<Post> rowMapper = new PostMapper();
        return this.jdbcTemplate.query(sql, rowMapper, '%' + query + '%');
    }

    public int savePost(Post post) {
        String sql = "insert into post(user,title,content,reg_date,updt_date) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, post.getUser(), post.getTitle(), post.getContent(), post.getRegDate(),
                post.getUpdtDate());
    }
}
