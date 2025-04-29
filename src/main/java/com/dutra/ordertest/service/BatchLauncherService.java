package com.dutra.ordertest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
public class BatchLauncherService {

    private final JobLauncher jobLauncher;
    private final Job orderBatchJob;
    private static final Logger logger = LoggerFactory.getLogger(BatchLauncherService.class);

    public BatchLauncherService(JobLauncher jobLauncher, Job orderBatchJob) {
        this.jobLauncher = jobLauncher;
        this.orderBatchJob = orderBatchJob;
    }

    public void launchBatch() throws Exception {
        logger.info("starting batch processing");
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(orderBatchJob, params);
    }
}
