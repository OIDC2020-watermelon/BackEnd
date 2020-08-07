package kr.or.watermelon.show.dto;

import kr.or.watermelon.show.entity.ThemeType;
import lombok.Data;

@Data
public class ThemeDto {
    private ThemeType type;

    private ProductForListDto product;
}
