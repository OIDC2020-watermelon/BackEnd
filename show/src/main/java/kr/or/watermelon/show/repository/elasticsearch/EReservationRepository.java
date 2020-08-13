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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EReservationRepository {

    private static final String indices = "logstash-*";
    private static final String PACKAGE_NAME = "kr.or.watermelon.ticket";
    private static final String SERVICE_NAME = "reservation";
    private final RestHighLevelClient client;
    private final ModelMapper modelMapper;

    public List<BucketDto> countProductReservationLog(Product product) throws IOException {
        SearchRequest sr = new SearchRequest().indices(indices);

        int unitDay = 1;
        String aggName = "range_by_@timestamp";
        String interceptorToFind = String.join(".", PACKAGE_NAME, SERVICE_NAME, "interceptor.ReservationInterceptor");
        DateRangeAggregationBuilder aggQuery = getAggRangeQueryBuilder(unitDay, product.getReleaseStartTime(), product.getReleaseEndTime(), aggName);
        BoolQueryBuilder boolQuery = reservationQuery()
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

    private BoolQueryBuilder reservationQuery() {
        return QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("service", SERVICE_NAME));
    }

    private DateRangeAggregationBuilder getAggRangeQueryBuilder(int day, LocalDateTime releaseStartTime, LocalDateTime releaseEndTime, String aggName) {
        DateRangeAggregationBuilder aggQuery = AggregationBuilders
                .dateRange(aggName)
                .field("@timestamp");

        while (releaseStartTime.isBefore(releaseEndTime)) {
            LocalDateTime nextStartTime = releaseStartTime.plusDays(Long.valueOf(day));
            aggQuery.addRange(releaseStartTime.toString(), nextStartTime.toString());
            releaseStartTime = nextStartTime;
        }
        return aggQuery;
    }


}
