package kr.or.watermelon.show.job.step;

import kr.or.watermelon.show.dto.BucketDto;
import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.repository.ProductRepository;
import kr.or.watermelon.show.repository.elasticsearch.EReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class UpdateProductPodStepConfig {

    private static final String NAME = "updateProductPodStep";
    private final ProductRepository productRepository;
    private final EReservationRepository eReservationRepository;
    private final StepBuilderFactory stepBuilderFactory;
    @Value("${traffic_upper_limit:10000}")
    private int trafficUpperLimit;

    @Value("${chunk_size:1000}")
    private int chunkSize;

    @JobScope
    @Bean(NAME + "Step")
    public Step step() {
        return stepBuilderFactory.get(NAME + "Step")
                .<Product, Product>chunk(chunkSize)
                .reader(reader(null, null))
                .writer(writer())
                .build();
    }

    @StepScope
    @Bean(NAME + "Reader")
    public RepositoryItemReader<Product> reader(
            @Value("#{jobParameters[startDateTime]}") String startDateTime,
            @Value("#{jobParameters[endDateTime]}") String endDateTime) {
        return new RepositoryItemReaderBuilder()
                .repository(productRepository)
                .methodName("findAllByReleaseEndTimeBetween")
                .pageSize(chunkSize)
                .arguments(Arrays.asList(LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime)))
                .name("reservationRepositoryItemReader")
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .build();
    }

    @StepScope
    @Bean(NAME + "Writer")
    public ItemWriter<Product> writer() {
        return products -> products.forEach(this::setProductPod);
    }

    private void setProductPod(Product p) {
        try {
            List<BucketDto> bucketDtos = eReservationRepository.countRangeLog(p,
                    "interceptor.ReservationInterceptor",
                    Duration.between(p.getReleaseStartTime(), p.getReleaseEndTime()).toDays());
            if (bucketDtos == null || bucketDtos.size() < 1) {
                p.setPod(0);
                return;
            }

            p.setPod((int) (bucketDtos.get(0).getDocCount() / trafficUpperLimit));
            productRepository.save(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
