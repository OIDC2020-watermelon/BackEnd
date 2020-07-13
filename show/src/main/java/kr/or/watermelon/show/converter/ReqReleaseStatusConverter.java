package kr.or.watermelon.show.converter;

import kr.or.watermelon.show.dto.ReqReleaseStatus;
import org.springframework.core.convert.converter.Converter;

public class ReqReleaseStatusConverter implements Converter<String, ReqReleaseStatus> {
    @Override
    public ReqReleaseStatus convert(String source) {
        return ReqReleaseStatus.valueOf(source.toUpperCase());
    }
}