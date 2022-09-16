package com.illtamer.sillage.rear.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("music")
public class Music {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 歌曲名
     * */
    private String name;

    /**
     * 作者
     * */
    private String artist;

    private String url;

    /**
     * 封面 URL
     * */
    private String cover;

    /**
     * 歌词 URL
     * */
    private String lrc;

    /**
     * 主题色
     * */
    private String theme;

}
