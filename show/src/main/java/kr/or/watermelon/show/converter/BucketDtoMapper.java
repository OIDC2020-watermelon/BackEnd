package kr.or.watermelon.show.converter;

import kr.or.watermelon.show.dto.BucketDto;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.modelmapper.PropertyMap;

public class BucketDtoMapper extends PropertyMap<Range.Bucket, BucketDto> {
    @Override
    protected void configure() {
        map().setFrom(source.getFromAsString());
        map().setTo(source.getToAsString());
    }
}
