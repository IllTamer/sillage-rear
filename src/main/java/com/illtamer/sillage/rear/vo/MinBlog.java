package com.illtamer.sillage.rear.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class MinBlog {

    private Integer id;

    private String title;

    private Timestamp createTime;

    private Integer flag;

}
