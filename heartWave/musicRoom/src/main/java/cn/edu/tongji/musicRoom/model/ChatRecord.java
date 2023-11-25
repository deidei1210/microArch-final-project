package cn.edu.tongji.musicRoom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRecord {
    private int id;
    private String content;
    private String type;
    private int userId;
    private int musicRoomId;
    private boolean isDeleted;
}
