package kr.or.watermelon.show.converter;

import kr.or.watermelon.show.entity.ThemeType;
import org.springframework.core.convert.converter.Converter;

public class ThemeTypeConverter implements Converter<String, ThemeType> {
    @Override
    public ThemeType convert(String source) {
        return ThemeType.valueOf(source.toUpperCase());
    }
}