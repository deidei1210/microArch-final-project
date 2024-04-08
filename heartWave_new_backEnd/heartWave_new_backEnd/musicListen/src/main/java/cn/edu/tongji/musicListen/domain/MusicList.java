package cn.edu.tongji.musicListen.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "music_list")
public class MusicList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type; //  歌单类型，如：normal, like, dislike, listenRecord, singRecord, recommend, created, admin, download, album专辑
    @Column(name = "create_date")
    private LocalDate createDate; // 创建日期
    private String description; // 歌单描述
    private String src; // 封面图片源
    private Integer playCount; // 播放次数
    @Column(name = "is_deleted")
    private Boolean isDeleted; // 标记是否被删除
    @Column(name = "is_opened")
    private Boolean isOpened; // 标记是否公开（个人主页）

    @OneToMany(mappedBy = "musicList", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrackEntry> entries; // 歌单中的音乐列表条目
}