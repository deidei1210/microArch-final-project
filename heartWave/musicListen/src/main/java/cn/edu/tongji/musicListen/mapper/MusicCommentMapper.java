package cn.edu.tongji.musicListen.mapper;

import cn.edu.tongji.musicListen.model.MusicComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MusicCommentMapper {
    //在一首歌后插入评论
    int insertMusicComment(MusicComment musicComment);
    //删除某一条评论(通过该评论的id)
    void deleteMusicComment(int id);
    // 通过音乐评论的id获取音乐评论
    MusicComment selectMusicCommentById(int id);
    //获取这一首歌的评论数量
//    int getThisMusicCommentCount(int musicId);

}
