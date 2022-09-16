package com.illtamer.sillage.rear.vo;

import com.illtamer.sillage.rear.pojo.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class DetailBlog {

    private Integer id;

    private String title;

    @ToString.Exclude
    private String content;

    private String summary;

    private String cover;

    private Integer flag;

    private Integer views;

    private Boolean appreciation;

    private Boolean shareStatement;

    private Boolean commentable;

    private Timestamp createTime;

    private Timestamp updateTime;

    private MinUser minUser;

    private List<Tag> tags;

    private Collection<RateComment> rateComments;

}
