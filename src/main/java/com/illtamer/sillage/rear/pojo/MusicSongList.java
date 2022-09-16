package com.illtamer.sillage.rear.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("music_song_list")
public class MusicSongList {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("music_id")
    private Integer musicId;

    @TableField("song_list_id")
    private Integer songListId;

}
