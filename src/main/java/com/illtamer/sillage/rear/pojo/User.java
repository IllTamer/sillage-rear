package com.illtamer.sillage.rear.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

@Data
@TableName("user")
@NoArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String nick;

    private String username;

    private String password;

    private String email;

    private String avatar;

    /**
     * 用户类型
     * */
    private Integer type;

    @TableField(jdbcType = JdbcType.TIMESTAMP)
    private Date createTime;

    @TableField(jdbcType = JdbcType.TIMESTAMP)
    private Date updateTime;

}
