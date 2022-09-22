package com.illtamer.sillage.rear.vo;

import lombok.Data;

/**
 * 插入返回对象 VO
 * */
@Data
public class InsertResponse {

    /**
     * 操作是否成功
     * */
    private Boolean success;

    /**
     * 操作实体类 Id
     * */
    private Integer objectId;

}
