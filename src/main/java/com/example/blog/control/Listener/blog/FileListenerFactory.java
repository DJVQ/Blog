package com.example.blog.control.Listener.blog;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.example.blog.control.service.BlogService;
import com.example.blog.control.service.TagService;

@Component
@PropertySource(value = "classpath:static/config/mdConfig.properties")
public class FileListenerFactory {
    // 设置监听路径
    @Value("${markdown.path}")
    private String monitorDir;

    // 设置轮询间隔
    private final long interval = TimeUnit.SECONDS.toMillis(1);

    // 自动注入业务服务
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

    public FileAlterationMonitor getMonitor() {
        // 创建过滤器
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".md"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);

        // 装配过滤器
        // FileAlterationObserver observer = new FileAlterationObserver(new File(monitorDir));
        FileAlterationObserver observer = new FileAlterationObserver(new File(monitorDir), filter);

        // 向监听者添加监听器，并注入业务服务
        observer.addListener(new FileListener(blogService, tagService));

        // 返回监听者
        return new FileAlterationMonitor(interval, observer);
    }

    public String getMonitorDir() {
        return this.monitorDir;
    }
}
