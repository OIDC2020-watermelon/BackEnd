package kr.or.watermelon.show.repository;

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
public class ElasticRepository {

    private static final String indices = "logstash-*";
    private final RestHighLevelClient client;
    private final ModelMapper modelMapper;

    public List<BucketDto> countProductReservationLog(Product product) throws IOException {
        SearchRequest sr = new SearchRequest();
        SearchSourceBuilder sb = new SearchSourceBuilder();
        DateRangeAggregationBuilder aggQuery = AggregationBuilders
                .dateRange("range_by_@timestamp")
                .field("@timestamp");

        LocalDateTime releaseStartTime = product.getReleaseStartTime();
        LocalDateTime releaseEndTime = product.getReleaseEndTime();
        while (releaseStartTime.isBefore(releaseEndTime)) {
            LocalDateTime nextStartTime = releaseStartTime.plusHours(Long.valueOf(1));
            aggQuery.addRange(releaseStartTime.toString(), nextStartTime.toString());
            releaseStartTime = nextStartTime;
        }

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("service", "reservation"))
                .must(QueryBuilders.matchQuery("logger_name", "kr.or.watermelon.ticket.reservation.interceptor.ReservationInterceptor"))
                .must(QueryBuilders.matchQuery("product_id", product.getId()));

        sb.query(boolQuery)
                .aggregation(aggQuery);

        sr.indices(indices);
        sr.source(sb);
        SearchResponse response = client.search(sr);
        Range f = response.getAggregations().get("range_by_@timestamp");
        List<? extends Range.Bucket> buckets = f.getBuckets();
        return buckets.stream().map(b -> modelMapper.map(b, BucketDto.class))
                .collect(Collectors.toList());
    }
}
