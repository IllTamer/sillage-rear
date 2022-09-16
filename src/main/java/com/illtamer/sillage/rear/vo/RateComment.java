package com.illtamer.sillage.rear.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * 分级评论
 * <p>
 * 两级分级 parent -> son(s)
 * */
@Data
@NoArgsConstructor
public class RateComment {

    private Integer id;

    private String nick;

    private String email;

    private String content;

    private String avatar;

    private Timestamp createTime;

    private Integer blogId;

    private final List<RateComment> sonComments = new LinkedList<>();

}
