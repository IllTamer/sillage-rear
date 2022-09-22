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
import com.illtamer.sillage.rear.vo.InsertResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    @SuppressWarnings("unchecked")
    public List<Music> listBySongListId(Integer songListId) {
        final LambdaQueryWrapper<MusicSongList> wrapper = new LambdaQueryWrapper<>();
        final List<MusicSongList> musicSongLists = musicSongListMapper
                .selectList(wrapper.eq(MusicSongList::getSongListId, songListId));
        if (musicSongLists.size() == 0) return Collections.EMPTY_LIST;
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

    public boolean updateSongListById(SongList songList) {
        return songListMapper.updateById(songList) > 0;
    }

    public InsertResponse saveSongList(SongList songList) {
        InsertResponse response = new InsertResponse();
        response.setSuccess(songListMapper.insert(songList) > 0);
        response.setObjectId(songList.getId());
        return response;
    }

    public boolean removeSongListById(Integer id) {
        return songListMapper.deleteById(id) > 0;
    }

    /**
     * 将歌曲 Id 添加到歌单
     * */
    public boolean saveMusicToSongList(Integer id, Integer musicId) {
        MusicSongList musicSongList = new MusicSongList();
        musicSongList.setSongListId(id);
        musicSongList.setMusicId(musicId);
        return musicSongListMapper.insert(musicSongList) > 0;
    }

    public InsertResponse saveMusic(Music music) {
        InsertResponse response = new InsertResponse();
        response.setSuccess(save(music));
        response.setObjectId(music.getId());
        return response;
    }

    public boolean removeMusicFromSongList(Integer id, Integer musicId) {
        return musicSongListMapper.delete(Wrappers.lambdaQuery(MusicSongList.class)
                .eq(MusicSongList::getSongListId, id)
                .eq(MusicSongList::getMusicId, musicId)) > 0;
    }

}
