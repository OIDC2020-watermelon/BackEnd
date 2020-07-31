package kr.or.watermelon.show.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArtistForListDto {

    private String name;

    private String occupation;

    private LocalDateTime debutDate;

    private List<String> products;

    private String thumbnailImgUrl;

}
