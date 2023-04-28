package com.example.blog.control.Listener.blog;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import com.example.blog.control.service.BlogService;
import com.example.blog.control.service.TagService;

public class FileListener extends FileAlterationListenerAdaptor{
    // 声明业务服务
    private BlogService blogService;
    private TagService tagService;
    // 采用构造函数注入服务
    public FileListener(BlogService blogService, TagService tagService) {
        this.blogService = blogService;
        this.tagService = tagService;
    }

    // 文件创建执行
    @Override
    public void onFileCreate(File file) {
        try {
            blogService.addBlog(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 文件创建修改
    @Override
    public void onFileChange(File file) {
        try {
            blogService.updateBlogTime(file);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // 文件创建删除
    @Override
    public void onFileDelete(File file) {
        blogService.deleteBlog(file);
    }

    // 目录创建
    @Override
    public void onDirectoryCreate(File directory) {
        String filePath = directory.getParent().replace("\\", "/");
        tagService.addTagByPath(filePath, directory.getName());
    }

    // 目录修改
    @Override
    public void onDirectoryChange(File directory) {
    }

    // 目录删除
    @Override
    public void onDirectoryDelete(File directory) {
        String filePath = directory.getPath().replace("\\", "/");
        tagService.deleteTagByPath(filePath);
    }


    // 轮询开始
    @Override
    public void onStart(FileAlterationObserver observer) {
    }

    // 轮询结束
    @Override
    public void onStop(FileAlterationObserver observer) {
    }
}
