package kr.or.watermelon.show.dto;

import kr.or.watermelon.show.entity.Category;
import lombok.Data;

@Data
public class ResProductDto {

    private String title;

    private Category category;

    private String place;

    private String imgUrl;

    private String thumbnailImgUrl;

}