package com.illtamer.sillage.rear.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * 发布博客实体类 VO
 * */
@Data
@NoArgsConstructor
public class PublishBlog {

    private Integer id;

    private String title;

    private String content;

    private String summary;

    private String cover;

    private Integer flag;

    private Integer views;

    private Boolean appreciation;

    private Boolean shareStatement;

    private Boolean commentable;

    private Boolean recommend;

    private Boolean published;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer typeId;

    private List<Integer> tagIdList;

}
