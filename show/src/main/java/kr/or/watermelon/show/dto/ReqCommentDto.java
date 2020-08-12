package kr.or.watermelon.show.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqCommentDto {
    private String content;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
}
