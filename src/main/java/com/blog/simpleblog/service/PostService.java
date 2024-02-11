package com.blog.simpleblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.simpleblog.repository.PostJpaRepository;
import com.blog.simpleblog.repository.PostRepository;
import com.blog.simpleblog.vo.Post;

@Service
public class PostService {
    private static List<Post> posts;

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostJpaRepository postJpaRepository;

    public Post getPost(Long id) {
        Post post = postJpaRepository.findOneById(id);
        return post;
    }

    public List<Post> getPosts() {
        posts = postJpaRepository.findAllByOrderByUpdtDateDesc();
        return posts;
    }

    public List<Post> getPostsOrderByUpdtAsc() {
        posts = postJpaRepository.findAllByOrderByUpdtDateAsc();
        return posts;
    }

    public List<Post> getPostsOrderByRegDesc() {
        posts = postRepository.findPostOrderByRegDateDesc();
        return posts;
    }

    public List<Post> searchPostByContent(String query) {
        posts = postJpaRepository.findByContentContainingOrderByUpdtDateDesc(query);
        return posts;
    }

    public List<Post> searchPostByTitle(String query) {
        posts = postJpaRepository.findByTitleContainingOrderByUpdtDateDesc(query);
        return posts;
    }

    public boolean savePost(Post post) {
        Post result = postJpaRepository.save(post);
        boolean isSuccess = true;
        if (result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public boolean updatePost(Post post) {
        Post result = postJpaRepository.findOneById(post.getId());
        if (result == null) {
            return false;
        }
        if (!post.getTitle().isEmpty()) {
            result.setTitle(post.getTitle());
        }
        if (!post.getContent().isEmpty()) {
            result.setContent(post.getContent());
        }
        postJpaRepository.save(result);
        return true;
    }

    public boolean deletePost(Long id) {
        Post result = postJpaRepository.findOneById(id);
        if (result == null) {
            return false;
        }
        postJpaRepository.deleteById(id);
        return true;
    }
}
