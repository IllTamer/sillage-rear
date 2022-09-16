package com.illtamer.sillage.rear.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("tag")
@NoArgsConstructor
public class Tag {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

}
