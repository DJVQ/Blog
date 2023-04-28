package com.example.blog.data.repo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.data.entities.Tag;
import org.springframework.transaction.annotation.Transactional;

public interface TagRepo extends JpaRepository<Tag, String>, Serializable{

    List<Tag> findByUnShowAndParentIsNull(boolean unShow);

    Optional<Tag> findByPath(String path);

    Optional<Tag> findByTid(Long tid);

    @Transactional
    void deleteByPath(String path);


}
