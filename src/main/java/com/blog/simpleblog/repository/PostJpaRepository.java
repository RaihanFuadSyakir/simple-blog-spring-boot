package com.blog.simpleblog.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.simpleblog.vo.Post;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Serializable> {
    Post findOneById(Long id);

    List<Post> findAllByOrderByUpdtDateDesc();

    List<Post> findAllByOrderByUpdtDateAsc();

    List<Post> findByTitleContainingOrderByUpdtDateDesc(String query);

    List<Post> findByContentContainingOrderByUpdtDateDesc(String query);
}
