package com.illtamer.sillage.rear.vo;

import com.illtamer.sillage.rear.pojo.Type;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 管理页 Blog VO
 * */
@Data
public class ManageBlog {

    private Integer id;

    private String title;

    private Type type;

    private Boolean recommend;

    private Integer views;

    private Timestamp updateTime;

    private Boolean published;

}
