package kr.or.watermelon.show.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResArtistDto {

    private String name;

    private LocalDateTime birthDate;

    private LocalDateTime debutDate;

    private String description;

    private Integer height;

    private Integer weight;

    private String imgUrl;

    private String instagramUrl;

    private String twitterUrl;

    private String facebookUrl;

    private List<ResCareerDto> careers;
}
