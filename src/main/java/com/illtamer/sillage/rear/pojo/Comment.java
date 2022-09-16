package com.illtamer.sillage.rear.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;

@Data
@TableName("comment")
@NoArgsConstructor
public class Comment {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String nick;

    private String email;

    private String content;

    /**
     * 头像
     * */
    private String avatar;

    @TableField(jdbcType = JdbcType.TIMESTAMP)
    private Timestamp createTime;

    private Integer blogId;

    private Integer parentCommentId;

}
