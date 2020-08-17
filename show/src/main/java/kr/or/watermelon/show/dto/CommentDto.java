package kr.or.watermelon.show.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private Long userId;
    private String userName;
    private String content;
    private LocalDateTime createdDateTime;
}
