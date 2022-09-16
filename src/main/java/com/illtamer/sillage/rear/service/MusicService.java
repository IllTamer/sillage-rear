package com.illtamer.sillage.rear.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.illtamer.sillage.rear.mapper.MusicMapper;
import com.illtamer.sillage.rear.mapper.MusicSongListMapper;
import com.illtamer.sillage.rear.mapper.SongListMapper;
import com.illtamer.sillage.rear.pojo.Music;
import com.illtamer.sillage.rear.pojo.MusicSongList;
import com.illtamer.sillage.rear.pojo.SongList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService extends ServiceImpl<MusicMapper, Music> {

    private final SongListMapper songListMapper;
    private final MusicSongListMapper musicSongListMapper;

    public MusicService(
            SongListMapper songListMapper,
            MusicSongListMapper musicSongListMapper
    ) {
        this.songListMapper = songListMapper;
        this.musicSongListMapper = musicSongListMapper;
    }

    /**
     * 根据歌单Id获取歌曲列表
     * @param songListId {@link SongList#getId()}
     * */
    public List<Music> listBySongListId(Integer songListId) {
        final LambdaQueryWrapper<MusicSongList> wrapper = new LambdaQueryWrapper<>();
        final List<MusicSongList> musicSongLists = musicSongListMapper
                .selectList(wrapper.eq(MusicSongList::getSongListId, songListId));
        List<Integer> musicIdList = musicSongLists.stream()
                .map(MusicSongList::getMusicId)
                .collect(Collectors.toList());
        return this.listByIds(musicIdList);
    }

    /**
     * 获取歌单列表
     * */
    public List<SongList> listSongList() {
        return songListMapper.selectList(Wrappers.emptyWrapper());
    }

}
