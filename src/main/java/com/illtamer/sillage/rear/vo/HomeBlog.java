package com.illtamer.sillage.rear.vo;

import com.illtamer.sillage.rear.pojo.Tag;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class HomeBlog {

    private Integer id;

    private String title;

    private String summary;

    private String cover;

    private Integer views;

    private Timestamp createTime;

    // tag
    private List<Tag> tags;

    // user
    private MinUser minUser;

}
