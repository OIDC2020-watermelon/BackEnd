package kr.or.watermelon.show.dto;

import kr.or.watermelon.show.entity.ThemeType;
import lombok.Data;

@Data
public class ThemeDto {
    private ThemeType themeType;

    private ProductForListDto product;

    public String getThemeType() {
        return themeType.name();
    }
}
