package com.illtamer.sillage.rear.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("song_list")
@NoArgsConstructor
public class SongList {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String introduction;

}
