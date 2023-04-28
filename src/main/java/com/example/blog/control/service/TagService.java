package com.example.blog.control.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.data.entities.Tag;
import com.example.blog.data.repo.TagRepo;

@Service
public class TagService {
    @Autowired 
    TagRepo tagRepo;

    public void addTagByPath(String path, String name) {
        Tag parent = tagRepo.findById(path).orElse(null);
        Tag tag = new Tag();
        tag.setT_name(name);
        tag.setPath(path + "/" + name);
        tag.setUnShow(name.startsWith("$"));
        tag.setA_nums(0);
        tag.setParent(parent);
        tagRepo.save(tag);
    }


    public void deleteTagByPath(String path) {
        Optional<Tag> tag = tagRepo.findById(path);
        if (tag.orElse(null) != null) {
            tagRepo.deleteByPath(path);
        }
    }

    public Tag getTagByIdAndPlusOne(String path) {
        Tag tag = tagRepo.findById(path).orElse(null);
        if (tag != null) {
            tag.setA_nums(tag.getA_nums() + 1);
            tagRepo.save(tag);
        }
        return tag;
    }

    public void subOne(Tag tag) {
        if (tag != null) {
            tag.setA_nums(tag.getA_nums() - 1);
            tagRepo.save(tag);
        }
    }

    public List<Tag> getSortTags() {
        List<Tag> tags = tagRepo.findByUnShowAndParentIsNull(false);
        if (tags.isEmpty()) return null;
        return tags.stream().sorted(Comparator.comparing(Tag::getPath)).toList();
    }

    public Tag getTagById(Long tid) {
        return tagRepo.findByTid(tid).orElse(null);
    }
}
