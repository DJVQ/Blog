package com.example.blog.data.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag")
public class Tag {
    @Id
    private String path;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tid",nullable=false,insertable=false,updatable=false,columnDefinition="int not null UNIQUE key auto_increment")
    private Long tid;
    private String t_name;
    private boolean unShow;
    private Integer a_nums;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private Set<Blog> blogs;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "path")
    private Tag parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Tag> children;

}
