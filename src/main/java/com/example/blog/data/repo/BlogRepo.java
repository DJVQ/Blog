package com.example.blog.data.repo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.example.blog.data.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.data.entities.Blog;
import org.springframework.transaction.annotation.Transactional;

public interface BlogRepo extends JpaRepository<Blog, String>, Serializable{

    Optional<Blog> findByBid(Long bid);

    Optional<Blog> findByFullPath(String fullPath);

    @Transactional
    void deleteByFullPath(String path);

    List<Blog> findByTag(Tag tag);

    List<Blog> findByFullPathStartingWith(String path);
}
