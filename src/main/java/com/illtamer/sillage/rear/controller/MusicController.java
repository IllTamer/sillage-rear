package com.illtamer.sillage.rear.controller;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.service.MusicService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/music")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    /**
     * 根据歌单Id获取歌单歌曲
     * @param songListId 歌单Id
     * */
    @GetMapping(value = "/list/{songListId}")
    public Response getMusicList(@PathVariable Integer songListId) {
        return Response.success(musicService.listBySongListId(songListId));
    }

    /**
     * 获取歌单信息
     * */
    @GetMapping(value = "/song-list")
    public Response getSongList() {
        return Response.success(musicService.listSongList());
    }

}
