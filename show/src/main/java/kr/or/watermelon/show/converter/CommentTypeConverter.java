package kr.or.watermelon.show.converter;

import kr.or.watermelon.show.entity.CommentType;
import org.springframework.core.convert.converter.Converter;

public class CommentTypeConverter implements Converter<String, CommentType> {
    @Override
    public CommentType convert(String source) {
        return CommentType.valueOf(source.toUpperCase());
    }
}