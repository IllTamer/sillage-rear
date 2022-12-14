package com.illtamer.sillage.rear.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("blog_tag")
@NoArgsConstructor
public class BlogTag {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer tagId;

    private Integer blogId;

}
