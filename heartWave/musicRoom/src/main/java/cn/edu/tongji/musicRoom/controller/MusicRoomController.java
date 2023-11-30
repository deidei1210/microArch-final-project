package cn.edu.tongji.musicRoom.controller;

import cn.edu.tongji.musicRoom.client.MusicListenServiceClient;
import cn.edu.tongji.musicRoom.dto.*;
import cn.edu.tongji.musicRoom.model.MusicRoom;
import cn.edu.tongji.musicRoom.service.MusicRoomService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/music_room")
public class MusicRoomController {
    @Resource
    private MusicRoomService musicRoomService;
    @Autowired
    private MusicListenServiceClient musicListenServiceClient;

    @PostMapping
    public ResponseEntity<?> createMusicRoom(@RequestBody MusicRoomDTO musicRoomDTO) {
        try {
            MusicRoom musicRoom = musicRoomService.createMusicRoom(musicRoomDTO);
            return new ResponseEntity<>(musicRoom, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = "create music room failed";
            return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMusicRoomDetailed(@PathVariable("id") int musicRoomId) {
        try {
            MusicRoomDetailed musicRoomDetailed = musicRoomService.getMusicRoomDetailed(musicRoomId);
            return new ResponseEntity<>(musicRoomDetailed, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<?> getAllMusicRoom(@PathVariable("page") int page) {
        try {
            MusicRoomInfo musicRoomInfo = musicRoomService.getAllMusicRoom(page);
            return new ResponseEntity<>(musicRoomInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/close")
    public ResponseEntity<?> closeMusicRoom(@RequestBody CloseRequest closeRequest) {
        try {
            musicRoomService.closeMusicRoom(closeRequest);
            return new ResponseEntity<>(0, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/song")
    @Transactional
    public ResponseEntity<?> addMusicRoomSong(@RequestBody MusicRoomSongRequest request) {
        try {
            musicListenServiceClient.addMusicRoomSong(request);
            return ResponseEntity.ok(0);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
