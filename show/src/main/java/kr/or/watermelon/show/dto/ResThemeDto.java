package kr.or.watermelon.show.dto;

import kr.or.watermelon.show.entity.ThemeType;
import lombok.Data;

@Data
public class ResThemeDto {
    private ThemeType themeType;

    private ResProductDto product;

    public String getThemeType() {
        return themeType.name();
    }
}
