package com.illtamer.sillage.rear.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;

@Data
@TableName("blog")
@NoArgsConstructor
public class Blog {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    @ToString.Exclude
    private String content;

    /**
     * 文章摘要
     * */
    private String summary;

    /**
     * 封面图片地址
     * */
    private String cover;

    /**
     * 原创 / 转载
     * */
    private Integer flag;

    /**
     * 浏览次数
     * */
    private Integer views;

    /**
     * 赞赏是否开启
     * */
    private Boolean appreciation;

    /**
     * 转载声明是否开启
     * */
    private Boolean shareStatement;

    /**
     * 评论是否开启
     * */
    private Boolean commentable;

    /**
     * 是否推荐
     * */
    private Boolean recommend;

    /**
     * 是否已发布 (/草稿)
     * */
    private Boolean published;

    /**
     * 创建时间
     * */
    @TableField(jdbcType = JdbcType.TIMESTAMP)
    private Timestamp createTime;

    /**
     * 更新时间
     * */
    @TableField(jdbcType = JdbcType.TIMESTAMP)
    private Timestamp updateTime;

    private Integer typeId;

    private Integer userId;

}
