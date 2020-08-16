package kr.or.watermelon.show.repository.elasticsearch;

import kr.or.watermelon.show.dto.BucketDto;
import kr.or.watermelon.show.entity.Product;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.DateRangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EReservationRepository {

    private static final String indices = "logstash-*";
    private static final String SERVICE = "reservation";
    private static final String PACKAGE_NAME = "kr.or.watermelon.ticket";
    private final RestHighLevelClient client;
    private final ModelMapper modelMapper;

    public List<BucketDto> countRangeLog(Product product, String interceptor, long unitDay) throws Exception {
        if (!product.getReleaseStartTime().isBefore(product.getReleaseEndTime()))
            return null;

        SearchRequest sr = new SearchRequest().indices(indices);
        String aggName = "range_by_@timestamp";
        String interceptorToFind = String.join(".", PACKAGE_NAME, SERVICE, interceptor);
        DateRangeAggregationBuilder aggQuery = getAggRangeQueryBuilder(unitDay, product.getReleaseStartTime(), product.getReleaseEndTime(), aggName);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("service", SERVICE))
                .must(QueryBuilders.matchQuery("logger_name", interceptorToFind))
                .must(QueryBuilders.matchQuery("product_id", product.getId()));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(boolQuery).aggregation(aggQuery);
        sr.source(sourceBuilder);

        SearchResponse response = client.search(sr);
        Range f = response.getAggregations().get(aggName);
        List<? extends Range.Bucket> buckets = f.getBuckets();
        return buckets.stream()
                .map(b -> modelMapper.map(b, BucketDto.class))
                .collect(Collectors.toList());
    }

    private DateRangeAggregationBuilder getAggRangeQueryBuilder(Long day, LocalDateTime releaseStartTime, LocalDateTime releaseEndTime, String aggName) throws Exception {
        if (day <= 0) {
            throw new Exception("UNIT DAY는 무조건 양수여야합니다.");//TODO EXCEPTION공부하기
        }

        DateRangeAggregationBuilder aggQuery = AggregationBuilders
                .dateRange(aggName)
                .field("@timestamp");

        while (releaseEndTime.isAfter(releaseStartTime)) {
            LocalDateTime nextStartTime = releaseStartTime.plusDays(day);
            aggQuery.addRange(releaseStartTime.toString(), nextStartTime.toString());
            releaseStartTime = nextStartTime;
        }
        return aggQuery;
    }
}
