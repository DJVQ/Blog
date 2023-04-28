package com.example.blog.data.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long bid;
    private String fullPath;
    private String title;
    private String createdBy;
    private Date createdAt;
    private Date updateAt;
    private boolean cite;
    private String type;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public Blog(String fullPath, String title, String createdBy, Date createdAt, Date updateAt, boolean cite, String type, Tag tag) {
        this.fullPath = fullPath;
        this.title = title;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.cite = cite;
        this.type = type;
        this.tag = tag;
    }
}
