package kr.or.watermelon.show.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final JobLauncher jobLauncher;
    private final Job updateProductPodJob;

    @Scheduled(cron = "0 0 3 * * ? *")
    public void updateProductPod() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        LocalDateTime now = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                TimeZone.getDefault().toZoneId());
        jobLauncher.run(updateProductPodJob, new JobParametersBuilder()
                .addString("endDateTime", now.toString())
                .addString("startDateTime", now.minusDays(1).toString())
                .toJobParameters());
    }
}