package kr.or.watermelon.show.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArtistDto {

    private String name;

    private LocalDateTime birthDate;

    private LocalDateTime debutDate;

    private List<CurtProductDto> products;

    private String description;

    private Integer height;

    private Integer weight;

    private String imgUrl;

    private String instagramUrl;

    private String twitterUrl;

    private String facebookUrl;

    private List<CareerDto> careers;
}
