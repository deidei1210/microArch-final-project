package cn.edu.tongji.musicListen.mapper;

import cn.edu.tongji.musicListen.model.PlayCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayCountMapper {
    // 添加playCount数据
    int insertPlayCount(PlayCount playcount);

    // 获取一个用户的播放次数
    List<PlayCount> getListenRecordListByUserId(int userId);

    List<PlayCount> getByUserIdMusicId(@Param("playCounts") List<PlayCount> playCounts);

    // 增加播放次数
}
