package com.illtamer.sillage.rear.controller.admin;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.pojo.Music;
import com.illtamer.sillage.rear.pojo.SongList;
import com.illtamer.sillage.rear.service.MusicService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin/music")
public class AdminMusicController {

    private final MusicService musicService;

    public AdminMusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Response removeMusic(
            @PathVariable Integer id
    ) {
        return Response.success(musicService.removeById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Response addMusic(@RequestBody Music music) {
        return Response.success(musicService.saveMusic(music));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/song-list/{id}")
    public Response removeSongList(
            @PathVariable Integer id
    ) {
        return Response.success(musicService.removeSongListById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/song-list")
    public Response updateSongList(@RequestBody SongList songList) {
        return Response.success(musicService.updateSongListById(songList));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/song-list")
    public Response addSongList(@RequestBody SongList songList) {
        return Response.success(musicService.saveSongList(songList));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/song-list/music/{id}")
    public Response addMusicToSongList(@PathVariable Integer id, @RequestParam("musicId") Integer musicId) {
        return Response.success(musicService.saveMusicToSongList(id, musicId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/song-list/music/{id}")
    public Response removeMusicFromSongList(@PathVariable Integer id, @RequestParam("musicId") Integer musicId) {
        return Response.success(musicService.removeMusicFromSongList(id, musicId));
    }

}
