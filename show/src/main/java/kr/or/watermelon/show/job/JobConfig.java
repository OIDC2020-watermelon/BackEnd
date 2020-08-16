package kr.or.watermelon.show.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfig {
    private static final String NAME = "updateProductPodJob";
    private final JobBuilderFactory jobBuilderFactory;
    private final Step updateProductPodStep;

    @Bean
    public Job updateProductPodJob() {
        return jobBuilderFactory.get(NAME)
                .start(updateProductPodStep)
                .build();
    }
}