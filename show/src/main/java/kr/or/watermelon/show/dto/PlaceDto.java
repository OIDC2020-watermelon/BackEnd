package kr.or.watermelon.show.dto;

import lombok.Data;

@Data
public class PlaceDto {

    private Long id;

    private String name;

    private String address;

    private String telephone;

    private String homepage;

    private String description;

    private String imgUrl;

    private String thumbnailImgUrl;


}
