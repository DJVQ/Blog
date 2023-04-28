package com.example.blog.control.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.data.entities.Blog;
import com.example.blog.data.entities.Tag;
import com.example.blog.data.repo.BlogRepo;
import com.example.blog.util.TimeUtil;

@Service
public class BlogService {
    @Autowired
    private BlogRepo blogRepo;
    @Autowired
    private TagService tagService;

    public Optional<Blog> getBlogByBid(Long bid) {
        return blogRepo.findByBid(bid);
    }

    public void addBlog(File mdFile) throws IOException, ParseException {
        String mdFilePath = mdFile.getPath().replace("\\", "/");
        String path = mdFilePath.substring(0, mdFilePath.lastIndexOf("/"));
        String name = mdFile.getName();
        String title, type, createBy = "";
        Date createdAt = null;
        boolean cite = false;
        BufferedReader br = new BufferedReader(new FileReader(mdFile));
        String line = null;
        while ((line = br.readLine()) != null) {
            if ("<!--".equals(line)) {
                continue;
            } else if("-->".equals(line)) {
                break;
            } else if(line.startsWith("createdBy:")) {
                createBy = line.replace("createdBy:", "");
                cite = !"ycq".equals(createBy);
            } else if(line.startsWith("createdAt:")) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                createdAt = format.parse(line.replace("createdAt:", ""));
            }
        }
        br.close();
        Tag tag = tagService.getTagByIdAndPlusOne(path);
        title = name.substring(0, name.lastIndexOf("."));
        type = name.substring(name.lastIndexOf("."), name.length());
        blogRepo.save(new Blog(mdFilePath, title, createBy, createdAt, new Date(), cite, type, tag));
    }

    public void deleteBlog(File file) {
        String fullPath = file.getPath().replace("\\", "/");
        Blog blog = blogRepo.findByFullPath(fullPath).orElse(null);
        if (blog != null) {
            Tag tag = blog.getTag();
            blogRepo.deleteByFullPath(fullPath);
            tagService.subOne(tag);
        }
    }

    public void updateBlogTime(File file) throws IOException, ParseException {
        String fullPath = file.getPath().replace("\\", "/");
        Optional<Blog> blogOptional = blogRepo.findByFullPath(fullPath);
        if (blogOptional.orElse(null) != null) {
            Blog blog = blogOptional.get();
            blog.setUpdateAt(new Date());
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                if ("<!--".equals(line)) {
                    continue;
                } else if("-->".equals(line)) {
                    break;
                } else if(line.startsWith("createdBy:")) {
                    String createBy = line.replace("createdBy:", "");
                    blog.setCreatedBy(createBy);
                    blog.setCite(!"ycq".equals(createBy));
                } else if(line.startsWith("createdAt:")) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    blog.setCreatedAt(format.parse(line.replace("createdAt:", "")));
                }
            }
            br.close();
            blogRepo.save(blog);
        }
    }
    
    /**
     * Map<年, Map<月, List<Blog>>>
     * @return 排序好的blogs
     */
    public Map<String, Map<String, List<Blog>>> getAllBlogsSortByTime() {
        Map<String, Map<String, List<Blog>>> sortBlogs = new TreeMap<>();
        List<Blog> allBlogs = blogRepo.findAll();
        allBlogs = allBlogs.stream().sorted(Comparator.comparing(Blog::getCreatedAt)).collect(Collectors.toList());
        for (Blog blog : allBlogs) {
            // if (blog.getTag() != null && blog.getTag().isShow());
            Date createdAt = blog.getCreatedAt();
            if (createdAt == null) {
                continue;
            }
            String year = String.valueOf(TimeUtil.getDateYear(createdAt));
            if (!sortBlogs.containsKey(year)) {//年
                sortBlogs.put(year, new TreeMap<>());
            } 
            Map<String, List<Blog>> yearBlogs = sortBlogs.get(year);
            int m = TimeUtil.getDateMonth(createdAt);
            String month = m > 9 ? String.valueOf(m) : "0" + String.valueOf(m);
            if (!yearBlogs.containsKey(month)) {//月
                yearBlogs.put(month, new ArrayList<>());
            }
            yearBlogs.get(month).add(blog);//日
        }
        return sortBlogs;
    }

    public List<Blog> getTagBlogsSortByTime(Long tid) {
        Tag tag = tagService.getTagById(tid);
        List<Blog> blogs = blogRepo.findByFullPathStartingWith(tag.getPath());
        if(blogs.isEmpty()){
            return null;
        }
        return  blogs.stream().sorted(Comparator.comparing(Blog::getCreatedAt)).toList();
    }
}
