package com.example.blog.control.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.blog.control.Listener.blog.FileListenerFactory;
import com.example.blog.control.service.TagService;
import com.example.blog.data.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.blog.control.service.BlogService;
import com.example.blog.data.entities.Blog;
import com.example.blog.util.FileUtil;
import com.example.blog.util.MarkdownUtil;

@RestController
public class BlogController {
    
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

    @Autowired
    private FileListenerFactory fileListenerFactory;

     @RequestMapping("/")
     public ModelAndView getIndex() {
         ModelAndView modelAndView = new ModelAndView();
         String indexMd = FileUtil.readByLines(fileListenerFactory.getMonitorDir() + "/$index.md");
         String indexContent = MarkdownUtil.markdownToHtmlExtensitons(indexMd);
         modelAndView.setViewName("index");
         modelAndView.addObject("index_content", indexContent);
         return modelAndView;
     }

    @RequestMapping("/ycq")
    public ModelAndView getIndex1() {
        ModelAndView modelAndView = new ModelAndView();
        String indexMd = FileUtil.readByLines(fileListenerFactory.getMonitorDir() + "/$index.md");
        String indexContent = MarkdownUtil.markdownToHtmlExtensitons(indexMd);
        modelAndView.setViewName("index");
        modelAndView.addObject("index_content", indexContent);
        return modelAndView;
    }

    @RequestMapping("/blog")
    public ModelAndView getBlogMd(@RequestParam("bid") Long bid) {
        Optional<Blog> blog = blogService.getBlogByBid(bid);
        ModelAndView modelAndView = new ModelAndView();
        String blogContent = FileUtil.readByLines(blog.get().getFullPath());
        String markdownContent = MarkdownUtil.markdownToHtmlExtensitons(blogContent);
        modelAndView.addObject("markdown_content", markdownContent);
        modelAndView.addObject("blog", blog.get());
        modelAndView.setViewName("blog");
        return modelAndView;
    }

    @RequestMapping("/posts")
    public ModelAndView getAllPosts() {
        Map<String, Map<String, List<Blog>>> blogs = blogService.getAllBlogsSortByTime();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blogs", blogs);
        modelAndView.setViewName("posts");
        return modelAndView;
    }

    @RequestMapping("/tags")
    public ModelAndView getAllTags() {
        List<Tag> tags = tagService.getSortTags();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tags", tags);
        modelAndView.setViewName("tags");
        return modelAndView;
    }

    @RequestMapping("/tag")
    public ModelAndView getTagBlogs(@RequestParam("tid") Long tid) {
        List<Blog> blogs = blogService.getTagBlogsSortByTime(tid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blogs", blogs);
        modelAndView.setViewName("tag");
        return modelAndView;
    }
    
}
